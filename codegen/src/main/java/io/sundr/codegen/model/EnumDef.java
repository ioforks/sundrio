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

package io.sundr.codegen.model;

import io.sundr.builder.TypedVisitor;
import io.sundr.codegen.model.EnumDefBuilder;
import io.sundr.codegen.model.*;
import io.sundr.codegen.model.AbstractTypeDef;
import io.sundr.codegen.model.ClassDef;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.TypeDef;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class EnumDef extends AbstractTypeDef<io.sundr.codegen.model.EnumDef, EnumDefBuilder> {

    private final Set<Property> properties;
    private final Set<Method> constructors;
    private final Set<Method> methods;

    public EnumDef(String packageName, String name, Set<ClassRef> annotations, Set<ClassRef> implementsList, Set<TypeDef> innerTypes, TypeDef outerType, Set<Property> properties, Set<Method> constructors, Set<Method> methods, int modifiers, Map<String, Object> attributes) {
        super(Kind.ENUM, packageName, name, annotations, ClassDef.EXTENDS_OBJECT, implementsList, outerType, innerTypes, modifiers, attributes);
        this.properties = properties;
        this.constructors = constructors;
        this.methods = methods;
    }

    @Override
    public EnumDefBuilder edit() {
        return new EnumDefBuilder(this);
    }

    @Override
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

        io.sundr.codegen.model.EnumDef typeDef = (io.sundr.codegen.model.EnumDef) o;

        if (getPackageName() != null ? !getPackageName().equals(typeDef.getPackageName()) : typeDef.getPackageName() != null) return false;
        return getName() != null ? getName().equals(typeDef.getName()) : typeDef.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getPackageName() != null ? getPackageName().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
