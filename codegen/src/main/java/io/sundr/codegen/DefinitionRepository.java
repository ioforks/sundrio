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

package io.sundr.codegen;

import io.sundr.codegen.model.*;
import io.sundr.codegen.model.ClassDefBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class DefinitionRepository {

    private static DefinitionRepository INSTANCE;

    private final Map<String, TypeDef> definitions = new HashMap<String, TypeDef>();

    private DefinitionRepository() {
    }

    public static synchronized final DefinitionRepository getRepository() {
        if (INSTANCE == null) {
            INSTANCE = new DefinitionRepository();
        }
        return INSTANCE;
    }

    public ClassDef register(ClassDef definition) {
        if (definition != null) {
            definitions.put(definition.getFullyQualifiedName(), definition);
        }
        return definition;
    }


    public ClassDef register(ClassDef definition, String... flags) {
        ClassDefBuilder builder = new ClassDefBuilder(definition);
        for (String flag : flags) {
            builder.addToAttributes(flag, true);
        }
        return register(builder.build());
    }


    public Set<TypeDef> getDefinitions(String... flags) {

        Set<TypeDef> result = new LinkedHashSet<TypeDef>();
        for (TypeDef candidate : definitions.values()) {
            boolean matches = true;
            for (String flag :flags) {
                if (!candidate.getAttributes().containsKey(flag) || !((Boolean) candidate.getAttributes().get(flag))) {
                   matches = false;
                   break;
                }
            }
            if (matches) {
                result.add(candidate);
            }
        }

        return Collections.unmodifiableSet(result);
    }

    public TypeDef getDefinition(String fullyQualifiedName) {
       return definitions.get(fullyQualifiedName);
    }

    public ClassDef getDefinition(ClassRef type) {
        if (type instanceof ClassRef) {
            return (ClassDef) definitions.get((type).getDefinition().getFullyQualifiedName());
        }
        return null;
    }

    public void clear() {
        definitions.clear();
    }
}
