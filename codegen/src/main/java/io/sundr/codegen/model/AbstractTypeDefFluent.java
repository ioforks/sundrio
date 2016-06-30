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

import io.sundr.builder.Builder;
import io.sundr.builder.Nested;
import io.sundr.codegen.model.ClassDef;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefFluent;
import io.sundr.codegen.model.EnumDef;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.TypeDef;

import java.util.Set;

public interface AbstractTypeDefFluent<T extends TypeDef<T,B>,B extends Builder<T>,A extends AbstractTypeDefFluent<T,B,A>> extends io.sundr.codegen.model.ModifierSupportFluent<A> {


    public Kind getKind();
    public A withKind(Kind kind);
    public String getPackageName();
    public A withPackageName(String packageName);
    public String getName();
    public A withName(String name);
    public A addToAnnotations(ClassRef... items);
    public A removeFromAnnotations(ClassRef... items);
    public Set<ClassRef> getAnnotations();
    public A withAnnotations(Set<ClassRef> annotations);
    public A withAnnotations(ClassRef... annotations);
    public AbstractTypeDefFluent.AnnotationsNested<A> addNewAnnotation();
    public AbstractTypeDefFluent.AnnotationsNested<A> addNewAnnotationLike(ClassRef item);
    public A addToExtendsList(ClassRef... items);
    public A removeFromExtendsList(ClassRef... items);
    public Set<ClassRef> getExtendsList();
    public A withExtendsList(Set<ClassRef> extendsList);
    public A withExtendsList(ClassRef... extendsList);
    public AbstractTypeDefFluent.ExtendsListNested<A> addNewExtendsList();
    public AbstractTypeDefFluent.ExtendsListNested<A> addNewExtendsListLike(ClassRef item);
    public A addToImplementsList(ClassRef... items);
    public A removeFromImplementsList(ClassRef... items);
    public Set<ClassRef> getImplementsList();
    public A withImplementsList(Set<ClassRef> implementsList);
    public A withImplementsList(ClassRef... implementsList);
    public AbstractTypeDefFluent.ImplementsListNested<A> addNewImplementsList();
    public AbstractTypeDefFluent.ImplementsListNested<A> addNewImplementsListLike(ClassRef item);
    public TypeDef getOuterType();
    public A withOuterType(TypeDef outerType);
    public A addToInnerTypes(TypeDef... items);
    public A removeFromInnerTypes(TypeDef... items);
    public Set<TypeDef> getInnerTypes();
    public A withInnerTypes(Set<TypeDef> innerTypes);
    public A withInnerTypes(TypeDef... innerTypes);
    public A addToClassDefInnerTypes(ClassDef... items);
    public A removeFromClassDefInnerTypes(ClassDef... items);
    public AbstractTypeDefFluent.ClassDefInnerTypesNested<A> addNewClassDefInnerType();
    public AbstractTypeDefFluent.ClassDefInnerTypesNested<A> addNewClassDefInnerTypeLike(ClassDef item);
    public A addToEnumDefInnerTypes(EnumDef... items);
    public A removeFromEnumDefInnerTypes(EnumDef... items);
    public AbstractTypeDefFluent.EnumDefInnerTypesNested<A> addNewEnumDefInnerType();
    public AbstractTypeDefFluent.EnumDefInnerTypesNested<A> addNewEnumDefInnerTypeLike(EnumDef item);

    public interface AnnotationsNested<N> extends Nested<N>,ClassRefFluent<AnnotationsNested<N>> {


    public N endAnnotation();    public N and();
}
    public interface ExtendsListNested<N> extends Nested<N>,ClassRefFluent<ExtendsListNested<N>> {


    public N endExtendsList();    public N and();
}
    public interface ImplementsListNested<N> extends Nested<N>,ClassRefFluent<ImplementsListNested<N>> {


    public N endImplementsList();    public N and();
}
    public interface ClassDefInnerTypesNested<N> extends Nested<N>,ClassDefFluent<AbstractTypeDefFluent.ClassDefInnerTypesNested<N>>{


    public N endClassDefInnerType();    public N and();
}
    public interface EnumDefInnerTypesNested<N> extends Nested<N>,EnumDefFluent<AbstractTypeDefFluent.EnumDefInnerTypesNested<N>>{

        
    public N endEnumDefInnerType();    public N and();
}


}
