/*
 *      Copyright 2019 The original authors.
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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.sundr.builder.annotations.Buildable;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.DefinitionScope;
import io.sundr.codegen.utils.StringUtils;

@Buildable(lazyCollectionInitEnabled = false)
public class ClassRef extends TypeRef {

  public static final String UNKNOWN = "<unknown>";
  public static final String BRACKETS = "[]";

  public static final ClassRef OBJECT = new ClassRefBuilder()
      .withFullyQualifiedName("java.lang.Object")
      .build();

  private final String fullyQualifiedName;
  private final int dimensions;
  private final List<TypeRef> arguments;

  public ClassRef(String fullyQualifiedName, int dimensions, List<TypeRef> arguments,
      Map<AttributeKey, Object> attributes) {
    super(attributes);
    this.dimensions = dimensions;
    this.arguments = arguments;
    this.fullyQualifiedName = fullyQualifiedName;
  }

  public TypeDef getDefinition() {
    return new TypeDefBuilder()
        .build();
  }

  public String getFullyQualifiedName() {
    return fullyQualifiedName;
  }

  public int getDimensions() {
    return dimensions;
  }

  public List<TypeRef> getArguments() {
    return arguments;
  }

  public ClassRef withDimensions(int dimensions) {
    return new ClassRefBuilder(this).withDimensions(dimensions).build();
  }

  public boolean isAssignableFrom(TypeRef other) {
    if (this == other) {
      return true;
    } else if (other == null) {
      return false;
    } else if (other instanceof PrimitiveRef) {
      if (getDefinition() == null) {
        return false;
      }

      if (getDefinition() != null && !JAVA_LANG.equals(getDefinition().getPackageName())) {
        return false;
      }

      if (!getDefinition().getName().toUpperCase().startsWith(((PrimitiveRef) other).getName().toUpperCase())) {
        return false;
      }
      return true;
    }

    if (!(other instanceof ClassRef)) {
      return false;
    }

    if (this == other || this.equals(other)) {
      return true;
    }

    return getDefinition().isAssignableFrom(((ClassRef) other).getDefinition());
  }

  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new LinkedHashSet<ClassRef>();
    for (TypeRef argument : arguments) {
      if (argument instanceof ClassRef) {
        refs.addAll(((ClassRef) argument).getReferences());
      }
    }
    refs.add(this);
    return refs;
  }

  /**
   * Checks if the ref needs to be done by fully qualified name. Why? Because an other reference
   * to a class with the same name but different package has been made already.
   */
  private boolean requiresFullyQualifiedName() {
    TypeDef definition = getDefinition();
    String currentPackage = DefinitionScope.get() != null ? DefinitionScope.get().getPackageName() : null;
    if (currentPackage != null) {
      if (definition != null && definition.getPackageName() != null && definition.getFullyQualifiedName() != null) {
        String conflictingFQCN = getDefinition().getFullyQualifiedName().replace(definition.getPackageName(), currentPackage);
        if (!conflictingFQCN.equals(getFullyQualifiedName())
            && DefinitionRepository.getRepository().getDefinition(conflictingFQCN) != null) {
          return true;
        }
      }
    }

    Map<String, String> referenceMap = DefinitionRepository.getRepository().getReferenceMap();
    if (referenceMap != null && referenceMap.containsKey(definition.getName())) {
      String fqn = referenceMap.get(definition.getName());
      if (!getDefinition().getFullyQualifiedName().equals(fqn)) {
        return true;
      }
    }
    return false;
  }

  public String getName() {
    if (requiresFullyQualifiedName()) {
      return getDefinition().getFullyQualifiedName();
    }
    return getDefinition().getName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    ClassRef classRef = (ClassRef) o;

    if (dimensions != classRef.dimensions)
      return false;
    if (getDefinition() != null ? !getDefinition().equals(classRef.getDefinition()) : classRef.getDefinition() != null)
      return false;
    return arguments != null ? arguments.equals(classRef.arguments) : classRef.arguments == null;
  }

  @Override
  public int hashCode() {
    int result = getDefinition() != null ? getDefinition().hashCode() : 0;
    result = 31 * result + dimensions;
    result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    TypeDef definition = getDefinition();
    if (definition == null) {
      sb.append(UNKNOWN);
    } else {
      if (requiresFullyQualifiedName()) {
        sb.append(definition.getPackageName()).append(DOT);
      }

      if (definition.getOuterType() != null) {
        sb.append(definition.getOuterType().getName()).append(DOT).append(definition.getName());
      } else {
        sb.append(definition.getName());
      }
    }
    if (arguments.size() > 0) {
      sb.append(LT);
      sb.append(StringUtils.join(arguments, COMA));
      sb.append(GT);
    }

    for (int i = 0; i < dimensions; i++) {
      sb.append(BRACKETS);
    }
    return sb.toString();
  }
}
