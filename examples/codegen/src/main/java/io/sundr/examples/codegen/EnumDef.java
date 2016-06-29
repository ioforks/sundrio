/*
 *      Copyright 2016 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.examples.codegen;

import io.sundr.builder.TypedVisitor;
import io.sundr.builder.annotations.Buildable;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Buildable(editableEnabled = false)
public class EnumDef extends AbstractTypeDef<EnumDef, EnumDefBuilder> {

    private final Set<Property> properties;
    private final Set<Method> constructors;
    private final Set<Method> methods;

    public EnumDef(String packageName, String name, Set<ClassRef> annotations, Set<ClassRef> implementsList, Set<TypeDef> innerTypes, TypeDef outerType, Set<Property> properties, Set<Method> constructors, Set<Method> methods, int modifiers, Map<String, Object> attributes) {
        super(Kind.ENUM, packageName, name, annotations, ClassDef.EXTENDS_OBJECT, implementsList, outerType, innerTypes, modifiers, attributes);
        this.properties = properties;
        this.constructors = constructors;
        this.methods = methods;
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

    public Set<ClassRef> getImports() {
        final Set<ClassRef> imports = new LinkedHashSet<ClassRef>();
        new EnumDefBuilder(this).accept(new TypedVisitor<ClassRefBuilder>() {
            public void visit(ClassRefBuilder builder) {
                imports.add(builder.build());
            }
        });
        return imports;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnumDef typeDef = (EnumDef) o;

        if (getPackageName() != null ? !getPackageName().equals(typeDef.getPackageName()) : typeDef.getPackageName() != null) return false;
        return getName() != null ? getName().equals(typeDef.getName()) : typeDef.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getPackageName() != null ? getPackageName().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public EnumDefBuilder edit() {
        return new EnumDefBuilder(this);
    }
}
