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

import io.sundr.builder.Builder;
import io.sundr.builder.annotations.Buildable;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractTypeDef<T extends TypeDef<T,B>, B extends Builder<T>> extends ModifierSupport implements TypeDef<T,B> {

    protected final Kind kind;
    protected final String packageName;
    protected final String name;

    protected final Set<ClassRef> annotations;
    protected final Set<ClassRef> extendsList;
    protected final Set<ClassRef> implementsList;

    protected final TypeDef outerType;
    protected final Set<TypeDef> innerTypes;

    @Buildable(editableEnabled = false)
    public AbstractTypeDef(Kind kind, String packageName, String name, Set<ClassRef> annotations, Set<ClassRef> extendsList, Set<ClassRef> implementsList, TypeDef outerType, Set<TypeDef> innerTypes, int modifiers, Map<String, Object> attributes) {
        super(modifiers, attributes);
        this.kind = kind;
        this.packageName = packageName;
        this.name = name;
        this.annotations = annotations;
        this.extendsList = extendsList;
        this.implementsList = implementsList;
        this.outerType = outerType;
        this.innerTypes = setOuterType(innerTypes, this);
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



    private static Set<TypeDef> setOuterType(Set<TypeDef> types, TypeDef outer) {
        Set<TypeDef> updated = new LinkedHashSet<TypeDef>();
        for (TypeDef<?,?> typeDef : types) {
            if (typeDef.getOuterType().equals(outer)) {
                updated.add(typeDef);
            } else {
                AbstractTypeDefBuilder<?,?> editor = (AbstractTypeDefBuilder) typeDef.edit();
                updated.add(editor.withOuterType(outer).build());
            }
        }
        return updated;
    }


    public Kind getKind() {
        return kind;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    public Set<ClassRef> getAnnotations() {
        return annotations;
    }

    public Set<ClassRef> getExtendsList() {
        return extendsList;
    }

    public Set<ClassRef> getImplementsList() {
        return implementsList;
    }

    public TypeDef getOuterType() {
        return outerType;
    }

    public Set<TypeDef> getInnerTypes() {
        return innerTypes;
    }
}
