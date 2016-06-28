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

import io.sundr.builder.annotations.Buildable;

import java.util.Map;


public abstract class TypeDef<T extends TypeDef<T,B>, B extends TypeDefBuilder<T,B>> extends ModifierSupport {

    private final Kind kind;
    private final String packageName;
    private final String name;
    private final TypeDef outerType;

    @Buildable
    public TypeDef(Kind kind, String packageName, String name, TypeDef outerType, int modifiers, Map<String, Object> attributes) {
        super(modifiers, attributes);
        this.kind = kind;
        this.packageName = packageName;
        this.name = name;
        this.outerType = outerType;
    }

    abstract B edit();

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

    public Kind getKind() {
        return kind;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    public TypeDef getOuterType() {
        return outerType;
    }

}
