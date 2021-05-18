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

package io.sundr.builder.internal.utils;

import static io.sundr.builder.Constants.DESCENDANTS;
import static io.sundr.builder.Constants.LAZY_COLLECTIONS_INIT_ENABLED;
import static io.sundr.builder.internal.functions.TypeAs.*;
import static io.sundr.model.Attributeable.ALSO_IMPORT;
import static io.sundr.model.Attributeable.DEFAULT_VALUE;
import static io.sundr.model.Attributeable.INIT;
import static io.sundr.model.Attributeable.LAZY_INIT;
import static io.sundr.model.utils.Types.isAbstract;
import static io.sundr.utils.Strings.capitalizeFirst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.Adapters;
import io.sundr.adapter.apt.AptContext;
import io.sundr.builder.Constants;
import io.sundr.builder.TypedVisitor;
import io.sundr.builder.annotations.*;
import io.sundr.builder.internal.BuildableRepository;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.Descendants;
import io.sundr.builder.internal.functions.TypeAs;
import io.sundr.model.AnnotationRef;
import io.sundr.model.Attributeable;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Kind;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Nameable;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.Statement;
import io.sundr.model.StringStatement;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamDefBuilder;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.Assignable;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.utils.Collections;
import io.sundr.model.utils.Optionals;
import io.sundr.model.utils.Types;

public class BuilderUtils {

  private static final String OBJECT_FULLY_QUALIFIED_NAME = Object.class.getName();
  private static final String[] NON_INLINABLE_PACKAGES = { "java", "javax", "sun", "com.sun" };

  private BuilderUtils() {
  }

  public static boolean isBuildable(TypeRef typeRef) {
    BuildableRepository repository = BuilderContextManager.getContext().getBuildableRepository();
    return repository.isBuildable(typeRef);
  }

  public static boolean isBuildable(TypeDef typeDef) {
    BuildableRepository repository = BuilderContextManager.getContext().getBuildableRepository();
    return repository.isBuildable(typeDef);
  }

  /**
   * Checks if {@link ClassRef} is buildable.
   * 
   * @param ref The reference.
   * @return True if buildable repository contains the ref or builder for the reference is present.
   */
  public static boolean isBuildable(ClassRef ref) {
    if (BuilderContextManager.getContext().getBuildableRepository().isBuildable(ref)) {
      return true;
    }

    String builderFQCN = ref.getFullyQualifiedName() + "Builder";
    TypeDef builder = BuilderContextManager.getContext().getDefinitionRepository().getDefinition(builderFQCN);
    if (builder == null) {
      return false;
    }

    return builder.getMethods()
        .stream()
        .filter(m -> "build".equals(m.getName()))
        .filter(m -> m.getReturnType() instanceof ClassRef)
        .map(m -> (ClassRef) m.getReturnType())
        .filter(r -> Assignable.isAssignable(r).from(ref))
        .count() > 0;
  }

  public static boolean isOrHasBuildableDescendants(Property property) {
    return isBuildable(property.getTypeRef())
        || (property.hasAttribute(DESCENDANTS) && !property.getAttribute(DESCENDANTS).isEmpty());
  }

  /**
   * Returns all references of a {@link ClassRef} that are considered buildable.
   * 
   * @param ref The reference.
   * @return The list with all detected references.
   */
  public static List<ClassRef> findBuildableReferences(ClassRef ref) {
    List<ClassRef> result = new ArrayList<>();
    TypeDef def = new TypeDefBuilder(GetDefinition.of(ref))
        .accept(new TypedVisitor<ClassRefBuilder>() {
          @Override
          public void visit(ClassRefBuilder builder) {
            ClassRef candidate = builder.build();
            if (isBuildable(candidate)) {
              result.add(candidate);
            }
          }
        })
        .build();
    return result;
  }

  public static ClassRef findBuildableSuperClassRef(TypeDef clazz) {
    BuildableRepository repository = BuilderContextManager.getContext().getBuildableRepository();

    for (ClassRef superClass : clazz.getExtendsList()) {
      if (repository.isBuildable(superClass)) {
        return superClass;
      }
    }
    return null;
  }

  public static TypeDef findBuildableSuperClass(TypeDef clazz) {
    BuildableRepository repository = BuilderContextManager.getContext().getBuildableRepository();

    for (ClassRef superClass : clazz.getExtendsList()) {
      if (repository.isBuildable(superClass)) {
        return repository.getBuildable(superClass);
      }
    }
    return null;
  }

  public static Method findBuildableConstructor(TypeDef clazz) {
    //1st pass go for annotated method
    for (Method candidate : clazz.getConstructors()) {
      if (hasBuildableAnnotation(candidate)) {
        return candidate;
      }
    }

    //2nd pass go for the first non-empty constructor
    for (Method candidate : clazz.getConstructors()) {
      if (candidate.getArguments().size() != 0) {
        return candidate;
      }
    }

    if (!clazz.getConstructors().isEmpty()) {
      return clazz.getConstructors().iterator().next();
    } else {
      throw new IllegalStateException("Could not find buildable constructor in: [" + clazz.getFullyQualifiedName() + "].");
    }
  }

  private static boolean hasBuildableAnnotation(Method method) {
    for (AnnotationRef annotationRef : method.getAnnotations()) {
      if (annotationRef.getClassRef().equals(Constants.BUILDABLE_ANNOTATION.getClassRef())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if method has a specific argument.
   * 
   * @param method The method.
   * @param property The argument.
   * @return True if matching argument if found.
   */
  public static boolean methodHasArgument(Method method, Property property) {
    for (Property candidate : method.getArguments()) {
      if (candidate.getName().equals(property.getName()) && candidate.getTypeRef().equals(property.getTypeRef())) {
        return true;
      }
    }
    return false;
  }

  public static boolean hasBuildableConstructorWithArgument(TypeDef clazz, Property property) {
    Method constructor = findBuildableConstructor(clazz);
    if (constructor == null) {
      return false;
    } else {
      return methodHasArgument(constructor, property);
    }

  }

  /**
   * Checks if there is a default constructor available.
   *
   * @param item The clazz to check.
   * @return True if default constructor is found, false otherwise.
   */
  public static boolean hasDefaultConstructor(TypeRef item) {
    DefinitionRepository repository = DefinitionRepository.getRepository();
    TypeDef def = repository.getDefinition(item);
    if (def == null && item instanceof ClassRef) {
      def = GetDefinition.of((ClassRef) item);
    }
    return hasDefaultConstructor(def);
  }

  /**
   * Checks if there is a default constructor available.
   *
   * @param item The clazz to check.
   * @return True if default constructor is found, false otherwise.
   */
  public static boolean hasDefaultConstructor(TypeDef item) {
    if (item == null) {
      return false;
    } else if (item.getConstructors().isEmpty()) {
      return true;
    } else {
      for (Method constructor : item.getConstructors()) {
        if (constructor.getArguments().size() == 0) {
          return true;
        }
      }
    }
    return false;
  }

  public static Set<Method> getInlineableConstructors(Property property) {
    Set<Method> result = new HashSet<Method>();
    TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_OPTIONAL_OF)
        .apply(property.getTypeRef());

    if (unwrapped instanceof ClassRef) {
      ClassRef classRef = (ClassRef) unwrapped;
      //We need to handle `new String(String str)` as a special case of Inlineable constructor and deprecate Inlineables of it before we acutally remove it, so here goes...
      if (classRef.getFullyQualifiedName().equals(String.class.getName())) {
        return Stream
            .of(GetDefinition.of(classRef).getConstructors().stream().filter(m -> m.getArguments().size() == 1).findFirst()
                .orElse(new MethodBuilder()
                    .withName("String")
                    .addNewArgument()
                    .withTypeRef(classRef)
                    .endArgument()
                    .build()))
            .collect(Collectors.toSet());
      }
      //We only want to inline non java types
      String pkg = Nameable.getPackageName(((ClassRef) unwrapped).getFullyQualifiedName());
      if (!Stream.of(NON_INLINABLE_PACKAGES).filter(s -> pkg.startsWith(s)).findAny().isPresent()) {
        for (Method candidate : GetDefinition.of((ClassRef) unwrapped).getConstructors()) {
          if (isInlineable(candidate)) {
            result.add(candidate);
          }
        }
      }
    }
    return result;
  }

  public static boolean isInlineable(Method method) {
    if (method.getArguments().size() == 0 || method.getArguments().size() > 5) {
      return false;
    }

    if (!method.getExceptions().isEmpty()) {
      return false;
    }

    // the method must be either public or protected
    if (!(method.isPublic() || method.isProtected())) {
      // the method is either private or package, so we cannot use it
      return false;
    }

    for (Property argument : method.getArguments()) {
      if (!(argument.getTypeRef() instanceof ClassRef)) {
        continue;
      } else if (((ClassRef) argument.getTypeRef()).getFullyQualifiedName().startsWith("java.lang")) {
        continue;
      } else {
        return false;
      }
    }
    return true;
  }

  public static TypeDef getInlineType(BuilderContext context, Inline inline) {
    try {
      return Adapters.adaptType(inline.type(), new AdapterContext(context.getDefinitionRepository()));
    } catch (MirroredTypeException e) {
      Element element = context.getTypes().asElement(e.getTypeMirror());
      AptContext aptContext = AptContext.create(context.getElements(), context.getTypes(), context.getDefinitionRepository());
      return Adapters.adaptType((TypeElement) element, aptContext);
    }
  }

  public static TypeDef getInlineReturnType(BuilderContext context, Inline inline, TypeDef fallback) {
    try {
      Class returnType = inline.returnType();
      if (returnType == null) {
        return fallback;
      }
      return Adapters.adaptType(inline.returnType(), new AdapterContext(context.getDefinitionRepository()));
    } catch (MirroredTypeException e) {
      if (None.FQN.equals(e.getTypeMirror().toString())) {
        return fallback;
      }

      Element element = context.getTypes().asElement(e.getTypeMirror());
      AptContext aptContext = AptContext.create(context.getElements(), context.getTypes(), context.getDefinitionRepository());
      return Adapters.adaptType((TypeElement) element, aptContext);
    }
  }

  public static Set<TypeElement> getBuildableReferences(BuilderContext context, Buildable buildable) {
    Set<TypeElement> result = new LinkedHashSet<TypeElement>();
    for (BuildableReference ref : buildable.refs()) {
      try {
        result.add(context.getElements().getTypeElement(ref.value().getCanonicalName()));
      } catch (MirroredTypeException e) {
        result.add(context.getElements().getTypeElement(e.getTypeMirror().toString()));
      }
    }
    return result;
  }

  public static Set<TypeElement> getBuildableReferences(BuilderContext context, ExternalBuildables buildable) {
    Set<TypeElement> result = new LinkedHashSet<TypeElement>();
    for (BuildableReference ref : buildable.refs()) {
      try {
        result.add(context.getElements().getTypeElement(ref.value().getCanonicalName()));
      } catch (MirroredTypeException e) {
        result.add(context.getElements().getTypeElement(e.getTypeMirror().toString()));
      }
    }
    return result;
  }

  public static TypeParamDef getNextGeneric(TypeDef type, TypeParamDef... excluded) {
    return getNextGeneric(type, Arrays.asList(excluded));
  }

  public static TypeParamDef getNextGeneric(TypeDef type, Collection<TypeParamDef> excluded) {
    Set<String> skip = new HashSet<String>();
    for (String s : allGenericsOf(type)) {
      skip.add(s);
    }

    for (TypeParamDef e : excluded) {
      skip.add(e.getName());
    }

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < GENERIC_NAMES.length; j++) {

        String name = GENERIC_NAMES[j] + ((i > 0) ? String.valueOf(i) : "");
        if (!skip.contains(name)) {
          return new TypeParamDefBuilder().withName(name).build();
        }
      }
    }
    throw new IllegalStateException("Could not allocate generic parameter letter for: " + type.getFullyQualifiedName());
  }

  public static Set<String> allGenericsOf(TypeDef clazz) {
    Set<String> result = new HashSet<String>();

    for (TypeParamDef paramDef : clazz.getParameters()) {
      result.add(paramDef.getName());
    }
    for (Property property : clazz.getProperties()) {
      result.addAll(allGenericsOf(property));
    }

    for (Method method : clazz.getMethods()) {
      result.addAll(allGenericsOf(method));
    }

    return result;
  }

  public static Set<String> allGenericsOf(TypeRef type) {
    Set<String> result = new HashSet<String>();
    if (type instanceof ClassRef) {
      for (TypeRef ref : ((ClassRef) type).getArguments()) {
        if (ref instanceof TypeParamRef) {
          result.add(((TypeParamRef) ref).getName());
        }
      }
    }
    return result;
  }

  public static Collection<String> allGenericsOf(Property property) {
    return allGenericsOf(property.getTypeRef());
  }

  public static Collection<String> allGenericsOf(Method method) {
    Set<String> result = new HashSet<String>(allGenericsOf(method.getReturnType()));
    for (Property property : method.getArguments()) {
      result.addAll(allGenericsOf(property));

    }
    return result;
  }

  private static final String[] GENERIC_NAMES = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
      "P", "Q", "R", "S" };

  public static String fullyQualifiedNameDiff(TypeRef typeRef, TypeDef originType) {
    Map<String, String> map = DefinitionRepository.getRepository().getReferenceMap();
    String currentPackage = originType != null ? originType.getPackageName() : null;

    if (typeRef instanceof ClassRef) {
      TypeRef unwrapped = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF, UNWRAP_MAP_VALUE_OF)
          .apply(typeRef);

      if (unwrapped instanceof ClassRef) {
        ClassRef classRef = (ClassRef) unwrapped;

        String candidateFqn = classRef.getFullyQualifiedName().replace(classRef.getPackageName(),
            currentPackage);

        //If classRef is inside the current package.
        if (candidateFqn.equals(classRef.getFullyQualifiedName())) {
          return "";
        }

        //If candidate is imported and different that the actual name, do a diff
        if (originType.getImports().contains(candidateFqn)
            && !classRef.getFullyQualifiedName().equals(candidateFqn)) {
          return capitalizeFirst(Types.fullyQualifiedNameDiff(candidateFqn, classRef.getFullyQualifiedName()));
        }

        //If not then we compare against what has been found in the map.
        String fqcn = map.get(classRef.getName());
        TypeDef mainDef = fqcn != null ? DefinitionRepository.getRepository().getDefinition(fqcn) : null;
        boolean mainBuildable = mainDef != null ? isBuildable(mainDef) : false;

        if (fqcn == null) {
          System.out.println("Warning: Expected to find class with name:" + classRef.getName());
        } else if (!classRef.getFullyQualifiedName().equals(fqcn) && mainBuildable) {
          return capitalizeFirst(Types.fullyQualifiedNameDiff(fqcn, classRef.getFullyQualifiedName()));
        }
      }
    }
    return "";
  }

  public static ClassRef buildableRef(TypeRef typeRef) {
    ClassRef unwrapped = (ClassRef) TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(typeRef);
    return isAbstract(unwrapped) || GetDefinition.of(unwrapped).getKind() == Kind.INTERFACE
        ? TypeAs.VISITABLE_BUILDER.apply(unwrapped)
        : TypeAs.BUILDER.apply(GetDefinition.of(unwrapped)).toInternalReference();
  }

  public static Property arrayAsList(Property property) {
    TypeRef typeRef = property.getTypeRef();
    TypeRef unwrapped = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(typeRef);
    ClassRef listRef = Collections.ARRAY_LIST.toReference(BOXED_OF.apply(unwrapped));

    if (Types.isPrimitive(unwrapped)) {
      return new PropertyBuilder(property).withTypeRef(Collections.LIST.toReference(BOXED_OF.apply(unwrapped)))
          .addToAttributes(LAZY_INIT, " new " + listRef + "()")
          .addToAttributes(INIT,
              property.hasAttribute(LAZY_COLLECTIONS_INIT_ENABLED) && property.getAttribute(LAZY_COLLECTIONS_INIT_ENABLED)
                  ? null
                  : " new " + listRef + "()")
          .addToAttributes(ALSO_IMPORT, alsoImport(property, listRef))
          .build();
    }

    return new PropertyBuilder(property)
        .withTypeRef(TypeAs.ARRAY_AS_LIST.apply(TypeAs.BOXED_OF.apply(unwrapped)))
        .build();
  }

  public static Property buildableField(Property property) {
    TypeRef typeRef = property.getTypeRef();
    ClassRef unwrapped = (ClassRef) TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(typeRef);
    ClassRef classRef = (ClassRef) typeRef;
    ClassRef builderType = !Types.isConcrete(unwrapped) ? TypeAs.VISITABLE_BUILDER.apply(unwrapped)
        : TypeAs.BUILDER.apply(GetDefinition.of(unwrapped)).toInternalReference();

    if (Types.isList(classRef)) {
      ClassRef listRef = Collections.ARRAY_LIST.toReference(builderType);
      return new PropertyBuilder(property).withTypeRef(Collections.LIST.toReference(builderType))
          .addToAttributes(LAZY_INIT, " new " + listRef + "()")
          .addToAttributes(INIT,
              property.hasAttribute(LAZY_COLLECTIONS_INIT_ENABLED) && property.getAttribute(LAZY_COLLECTIONS_INIT_ENABLED)
                  ? null
                  : " new " + listRef + "()")
          .addToAttributes(ALSO_IMPORT, alsoImport(property, listRef, builderType))
          .build();
    }

    if (Types.isSet(classRef)) {
      ClassRef setRef = Collections.LINKED_HASH_SET.toReference(builderType);
      return new PropertyBuilder(property).withTypeRef(Collections.SET.toReference(builderType))
          .addToAttributes(LAZY_INIT, " new " + setRef + "()")
          .addToAttributes(INIT,
              property.hasAttribute(LAZY_COLLECTIONS_INIT_ENABLED) && property.getAttribute(LAZY_COLLECTIONS_INIT_ENABLED)
                  ? null
                  : " new " + setRef + "()")
          .addToAttributes(ALSO_IMPORT, alsoImport(property, setRef, builderType))
          .build();
    }

    if (Types.isOptionalLong(classRef)) {
      ClassRef optionalRef = Optionals.OPTIONAL_LONG.toReference(builderType);
      return new PropertyBuilder(property).withTypeRef(optionalRef)
          .addToAttributes(INIT, " OptionalLong.empty()")
          .build();
    }

    if (Types.isOptionalDouble(classRef)) {
      ClassRef optionalRef = Optionals.OPTIONAL_DOUBLE.toReference(builderType);
      return new PropertyBuilder(property).withTypeRef(optionalRef)
          .addToAttributes(INIT, " OptionalDouble.empty()")
          .build();
    }

    if (Types.isOptionalInt(classRef)) {
      ClassRef optionalRef = Optionals.OPTIONAL_INT.toReference(builderType);
      return new PropertyBuilder(property).withTypeRef(optionalRef)
          .addToAttributes(INIT, " OptionalInt.empty()")
          .build();
    }

    if (Types.isOptional(classRef)) {
      ClassRef optionalRef = Optionals.OPTIONAL.toReference(builderType);
      return new PropertyBuilder(property).withTypeRef(optionalRef)
          .addToAttributes(INIT, " Optional.empty()")
          .addToAttributes(ALSO_IMPORT, alsoImport(property, optionalRef, builderType))
          .build();
    }

    if (Types.isConcrete(builderType) && BuilderUtils.hasDefaultConstructor(builderType)
        && property.hasAttribute(DEFAULT_VALUE)) {
      return new PropertyBuilder(property).withTypeRef(builderType)
          .addToAttributes(ALSO_IMPORT, alsoImport(property, builderType))
          .addToAttributes(INIT, "new " + builderType + "()")
          .build();
    }

    return new PropertyBuilder(property).withTypeRef(builderType)
        .addToAttributes(ALSO_IMPORT, alsoImport(property, builderType))
        .removeFromAttributes(INIT)
        .build();
  }

  public static List<ClassRef> alsoImportAsList(Attributeable attributeable) {
    List<ClassRef> result = new ArrayList<ClassRef>();
    if (attributeable.hasAttribute(ALSO_IMPORT)) {
      result.addAll(attributeable.getAttribute(ALSO_IMPORT));
    }
    return result;
  }

  public static List<ClassRef> alsoImport(Attributeable attributeable, ClassRef... refs) {
    List<ClassRef> result = alsoImportAsList(attributeable);
    result.addAll(Arrays.asList(refs));
    return result;
  }

  public static List<Statement> toHashCode(Collection<Property> properties) {
    List<Statement> statements = new ArrayList<>();
    statements.add(new StringStatement("return java.util.Objects.hash(" + Stream
        .concat(properties.stream().map(Property::getName), Stream.of("super.hashCode()")).collect(Collectors.joining(",  "))
        + ");"));
    return statements;
  }

  public static List<Statement> toEquals(TypeDef type, Collection<Property> properties) {
    List<Statement> statements = new ArrayList<>();

    String simpleName = type.getName();
    ClassRef superClass = type.getExtendsList().isEmpty() ? TypeDef.OBJECT_REF : type.getExtendsList().iterator().next();
    statements.add(new StringStatement("if (this == o) return true;"));
    statements.add(new StringStatement("if (o == null || getClass() != o.getClass()) return false;"));

    //If base fluent is the superclass just skip.
    final BuilderContext context = BuilderContextManager.getContext();
    final String superClassFQN = superClass.getFullyQualifiedName();
    if (!context.getBaseFluentClass().getFullyQualifiedName().equals(superClassFQN)
        && !OBJECT_FULLY_QUALIFIED_NAME.equals(superClassFQN)) {
      statements.add(new StringStatement("if (!super.equals(o)) return false;"));
    }
    statements.add(new StringStatement(
        new StringBuilder().append(simpleName).append(" that = (").append(simpleName).append(") o;").toString()));

    for (Property property : properties) {
      String name = property.getName();
      if (Types.isPrimitive(property.getTypeRef())) {
        statements.add(new StringStatement(new StringBuilder().append("if (").append(name).append(" != ").append("that.")
            .append(name).append(") return false;").toString()));
      } else if (property.getTypeRef() instanceof ClassRef
          && Descendants.isDescendant(type, GetDefinition.of((ClassRef) property.getTypeRef()))) {
        statements.add(new StringStatement(new StringBuilder()
            .append("if (").append(name).append(" != null &&").append(name).append(" != this ? !").append(name)
            .append(".equals(that.").append(name).append(") :")
            .append("that.").append(name).append(" != null &&").append(name).append(" != this ) return false;").append("\n")
            .toString()));
      } else {
        statements.add(new StringStatement(new StringBuilder().append("if (").append(name).append(" != null ? !").append(name)
            .append(".equals(that.").append(name).append(") :")
            .append("that.").append(name).append(" != null) return false;").toString()));

      }
    }

    statements.add(new StringStatement("return true;"));
    return statements;
  }
}
