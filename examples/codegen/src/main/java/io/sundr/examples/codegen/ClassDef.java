/*
 * Copyright 2016 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.examples.codegen;

import io.sundr.builder.annotations.Buildable;
import io.sundr.codegen.utils.StringUtils;

import java.util.*;

@Buildable
public class ClassDef extends AbstractTypeDef<ClassDef, ClassDefBuilder> {

    public static ClassDef OBJECT = new ClassDefBuilder()
            .withPackageName("java.lang")
            .withName("Object")
            .build();

    public static Set<ClassRef> EXTENDS_OBJECT = new LinkedHashSet<>(Arrays.asList(ClassDef.OBJECT.toInternalReference()));

    private final List<TypeParamDef> parameters;

    private final Set<Property> properties;
    private final Set<Method> constructors;
    private final Set<Method> methods;

    public ClassDef(Kind kind, String packageName, String name, Set<ClassRef> annotations, Set<ClassRef> extendsList, Set<ClassRef> implementsList, List<TypeParamDef> parameters, Set<Property> properties, Set<Method> constructors, Set<Method> methods, TypeDef outerType, Set<TypeDef> innerTypes, int modifiers, Map<String, Object> attributes) {
        super(kind, packageName, name, annotations, extendsList, implementsList, outerType, innerTypes, modifiers, attributes);
        this.parameters = parameters;
        this.properties = properties;
        this.constructors = adaptConstructors(constructors, this);
        this.methods = methods;
    }

    /**
     * The method adapts constructor method to the current class.
     * It unsets any name that may be presetn in the method.
     * It also sets as a return type a reference to the current type.
     * @param methods
     * @param target
     * @return
     */
    private static Set<Method> adaptConstructors(Set<Method> methods, ClassDef target) {
        Set<Method> adapted = new LinkedHashSet<Method>();
        for (Method m : methods) {
            adapted.add(new MethodBuilder(m)
                    .withName(null)
                    .withReturnType(target.toUnboundedReference())
                    .build());
        }
        return adapted;
    }

    /**
     * Returns the fully qualified name of the type.
     *
     * @return
     */
    public String getFullyQualifiedName() {
        StringBuilder sb = new StringBuilder();
        if (packageName != null && !packageName.isEmpty()) {
            sb.append(getPackageName()).append(".");
        }

        if (outerType != null) {
            sb.append(outerType.getName()).append(".");
        }
        sb.append(getName());

        return sb.toString();
    }

    public boolean isAssignableFrom(ClassDef o) {
        if (this == o || this.equals(o)) {
            return true;
        }

        if (packageName == null && "java.lang".equals(o.packageName) && name.equalsIgnoreCase(o.name)) {
            return true;
        }
        if (o.packageName == null && "java.lang".equals(packageName) && name.equalsIgnoreCase(o.name)) {
            return true;
        }

        for (ClassRef e : o.getExtendsList()) {
            if (isAssignableFrom(e.getDefinition())) {
                return true;
            }
        }

        for (ClassRef i : o.getImplementsList()) {
            if (isAssignableFrom(i.getDefinition())) {
                return true;
            }
        }


        return false;
    }

    public List<TypeParamDef> getParameters() {
        return parameters;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public Set<Method> getConstructors() {
        return constructors;
    }

    public Set<Method> getMethods() {
        return methods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassDef typeDef = (ClassDef) o;

        if (packageName != null ? !packageName.equals(typeDef.packageName) : typeDef.packageName != null) return false;
        return name != null ? name.equals(typeDef.name) : typeDef.name == null;

    }

    @Override
    public int hashCode() {
        int result = packageName != null ? packageName.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * Creates a {@link ClassRef} for the current definition with the specified arguments.
     * @param arguments The arguments to be passed to the reference.
     * @return
     */
    public ClassRef toReference(TypeRef... arguments) {
        List<TypeRef> actualArguments = new ArrayList<TypeRef>();
        for (int i = 0; i < parameters.size(); i++) {
            if (i < arguments.length) {
                actualArguments.add(arguments[i]);
            } else {
                actualArguments.add(new WildcardRef());
            }
        }
        return new ClassRefBuilder()
                .withDefinition(this)
                .withArguments(actualArguments)
                .build();
    }

    /**
     * Creates a {@link ClassRef} for internal use inside the scope of the type (methods, properties etc).
     * It uses as arguments the same 'letters' as the parameters definition.
     * @return
     */
    public ClassRef toInternalReference() {
        List<TypeRef> arguments = new ArrayList<TypeRef>();
        for (TypeParamDef parameter : parameters) {
            arguments.add(parameter.toReference());
        }
        return new ClassRefBuilder()
                .withDefinition(this)
                .withArguments(arguments)
                .build();
    }

    /**
     * Creates a {@link ClassRef} without bounds.
     * @return
     */
    public ClassRef toUnboundedReference() {
        return new ClassRefBuilder()
                .withDefinition(this)
                .withArguments(new TypeRef[0])
                .build();
    }

    public Set<String> getImports() {
        final Set<String> imports = new LinkedHashSet<String>();
        for (ClassRef ref : getReferences()) {
            if (ref .getDefinition().getPackageName() == null || ref .getDefinition().getPackageName().isEmpty() ||  ref.getDefinition().getPackageName().equals(packageName)) {
                continue;
            } else {
                imports.add(ref.getDefinition().getFullyQualifiedName());
            }

        }
        return imports;
    }

    public Set<ClassRef> getReferences() {
        final Set<ClassRef> refs = new LinkedHashSet<ClassRef>();

        for (ClassRef i : implementsList) {
            refs.addAll(i.getReferences());

        }

        for (ClassRef e : extendsList) {
            refs.addAll(e.getReferences());
        }

        for (Property property : properties) {
            refs.addAll(property.getReferences());
        }

        for (Method method : constructors) {
            refs.addAll(method.getReferences());
        }


        for (Method method : methods) {
            refs.addAll(method.getReferences());
        }

        for (TypeParamDef typeParamDef : parameters) {
            for (ClassRef bound : typeParamDef.getBounds()) {
                refs.addAll(bound.getReferences());
            }
        }

        for (TypeDef innerType : innerTypes) {
            refs.addAll(innerType.getReferences());
        }

        if (getAttributes().containsKey(ALSO_IMPORT)) {
            Object obj = getAttributes().get(ALSO_IMPORT);
            if (obj instanceof ClassRef) {
                refs.add((ClassRef) obj);
            } else if (obj instanceof Collection) {
                refs.addAll((Collection<? extends ClassRef>) obj);
            }
        }
        return refs;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isPublic()) {
            sb.append(PUBLIC).append(SPACE);
        } else if (isProtected()) {
            sb.append(PROTECTED).append(SPACE);
        } else if (isPrivate()) {
            sb.append(PRIVATE).append(SPACE);
        }
        if (isStatic()) {
            sb.append(STATIC).append(SPACE);
        }
        if (isAbstract()) {
            sb.append(ABSTRACT).append(SPACE);
        }
        if (isFinal()) {
            sb.append(FINAL).append(SPACE);
        }

        sb.append(kind.name().toLowerCase()).append(SPACE);
        sb.append(name);

        if (parameters != null && !parameters.isEmpty()) {
            sb.append(LT);
            sb.append(StringUtils.join(parameters, COMA));
            sb.append(GT);
        }

        if (extendsList != null && !extendsList.isEmpty()
                && (extendsList.size() != 1 || !extendsList.contains(OBJECT.toReference()))) {
            sb.append(SPACE).append(EXTENDS).append(SPACE);
            sb.append(StringUtils.join(extendsList, COMA));
        }

        if (implementsList != null && !implementsList.isEmpty()) {
            sb.append(SPACE).append(IMPLEMENTS).append(SPACE);
            sb.append(StringUtils.join(implementsList, COMA));
        }

        return sb.toString();
    }

    @Override
    public ClassDefBuilder edit() {
        return new ClassDefBuilder(this);
    }
}
