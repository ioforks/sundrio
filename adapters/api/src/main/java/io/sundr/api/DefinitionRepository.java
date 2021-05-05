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

package io.sundr.api;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.sundr.model.AttributeKey;
import io.sundr.model.ClassRef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;

public class DefinitionRepository {

  private static DefinitionRepository INSTANCE;

  private final ConcurrentMap<String, TypeDef> definitions = new ConcurrentHashMap<String, TypeDef>();
  private final ConcurrentMap<String, Supplier<TypeDef>> suppliers = new ConcurrentHashMap<String, Supplier<TypeDef>>();

  private DefinitionRepository() {
  }

  public static synchronized final DefinitionRepository getRepository() {
    if (INSTANCE == null) {
      INSTANCE = new DefinitionRepository();
    }
    return INSTANCE;
  }

  public synchronized void registerIfAbsent(String fqcn, Supplier<TypeDef> supplier) {
    if (definitions.containsKey(fqcn)) {
      return;
    }

    if (suppliers.containsKey(fqcn)) {
      return;
    }

    suppliers.put(fqcn, supplier);
  }

  public synchronized void registerIfAbsent(TypeDef definition) {
    if (definition == null) {
      return;
    }

    String fqcn = definition.getFullyQualifiedName();
    if (definitions.containsKey(fqcn)) {
      return;
    } else {
      definitions.put(fqcn, definition);
    }
  }

  public synchronized TypeDef register(TypeDef definition) {
    definitions.put(definition.getFullyQualifiedName(), definition);
    return definition;
  }

  public synchronized TypeDef register(TypeDef definition, String... flags) {
    TypeDefBuilder builder = new TypeDefBuilder(definition);
    for (String flag : flags) {
      builder.addToAttributes(new AttributeKey<Boolean>(flag, Boolean.class), true);
    }
    return register(builder.build());
  }

  public synchronized TypeDef register(TypeDef definition, AttributeKey<Boolean>... flags) {
    TypeDefBuilder builder = new TypeDefBuilder(definition);
    for (AttributeKey<Boolean> flag : flags) {
      builder.addToAttributes(flag, true);
    }
    return register(builder.build());
  }

  public synchronized Set<TypeDef> getDefinitions(String... flags) {
    Set<TypeDef> result = new LinkedHashSet<TypeDef>();
    for (TypeDef candidate : definitions.values()) {
      boolean matches = true;
      for (String flag : flags) {
        AttributeKey<Boolean> attributeKey = new AttributeKey<Boolean>(flag, Boolean.class);
        if (!candidate.hasAttribute(attributeKey) || !(candidate.getAttribute(attributeKey))) {
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

  public synchronized Set<TypeDef> getDefinitions(AttributeKey<Boolean>... attributeKeys) {

    Set<TypeDef> result = new LinkedHashSet<TypeDef>();
    for (TypeDef candidate : definitions.values()) {
      boolean matches = true;
      for (AttributeKey<Boolean> attributeKey : attributeKeys) {
        if (!candidate.hasAttribute(attributeKey) || !(candidate.getAttribute(attributeKey))) {
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

  public synchronized boolean hasDefinition(String fullyQualifiedName) {
    return definitions.containsKey(fullyQualifiedName) || suppliers.containsKey(fullyQualifiedName);
  }

  public synchronized TypeDef getDefinition(String fullyQualifiedName) {
    if (definitions.containsKey(fullyQualifiedName)) {
      return definitions.get(fullyQualifiedName);
    }
    if (suppliers.containsKey(fullyQualifiedName)) {
      TypeDef typeDef = suppliers.get(fullyQualifiedName).get();
      if (!definitions.containsKey(fullyQualifiedName)) {
        definitions.put(fullyQualifiedName, typeDef);
        return typeDef;
      } else {
        return definitions.get(fullyQualifiedName);
      }
    }
    return null;
  }

  public synchronized TypeDef getDefinition(TypeRef type) {
    if (type instanceof ClassRef) {
      return getDefinition(((ClassRef) type).getFullyQualifiedName());
    }
    return null;
  }

  public synchronized Collection<TypeDef> getDefinitions() {
    return Stream.concat(definitions.keySet().stream(), suppliers.keySet().stream()).distinct().map(k -> getDefinition(k))
        .collect(Collectors.toSet());
  }

  public synchronized void clear() {
    definitions.clear();
  }
}
