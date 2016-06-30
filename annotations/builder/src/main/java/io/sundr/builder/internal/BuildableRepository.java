/*
 * Copyright 2015 The original authors.
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

package io.sundr.builder.internal;

import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ParameterReference;
import io.sundr.codegen.model.ClassDef;
import io.sundr.codegen.model.TypeRef;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class BuildableRepository {

    private final Map<String, ClassDef> buildables = new HashMap<String, ClassDef>();

    public ClassDef register(ClassDef buildable) {
        if (buildable != null) {
            buildables.put(buildable.getFullyQualifiedName(), buildable);
        }
        return buildable;
    }

    public Set<ClassDef> getBuildables() {
        return Collections.unmodifiableSet(new LinkedHashSet<ClassDef>(buildables.values()));
    }

    public ClassDef getBuildable(TypeRef type) {
        if (type instanceof ClassRef) {
            return buildables.get(((ClassRef)type).getDefinition().getFullyQualifiedName());
        }
        return null;
    }

    public boolean isBuildable(ClassDef type) {
        return type != null && buildables.containsKey(type.getFullyQualifiedName());
    }

    public boolean isBuildable(TypeRef type) {
        if (type instanceof ClassRef) {
            return isBuildable(((ClassRef)type).getDefinition());
        }
        return false;
    }

    public void clear() {
        buildables.clear();
    }
}
