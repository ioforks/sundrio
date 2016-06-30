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
import io.sundr.builder.VisitableBuilder;
import io.sundr.codegen.model.AbstractTypeDef;
import io.sundr.codegen.model.ClassDef;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.ClassRefFluentImpl;
import io.sundr.codegen.model.EnumDef;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.TypeDef;

import java.util.LinkedHashSet;
import java.util.Set;

public class AbstractTypeDefFluentImpl<T extends TypeDef<T,B>,B extends Builder<T>,A extends AbstractTypeDefFluent<T,B,A>> extends io.sundr.codegen.model.ModifierSupportFluentImpl<A> implements AbstractTypeDefFluent<T,B,A>{

    private Kind kind;
    private String packageName;
    private String name;
    private Set<VisitableBuilder<? extends ClassRef,?>> annotations =  new LinkedHashSet<VisitableBuilder<? extends ClassRef,?>>();
    private Set<VisitableBuilder<? extends ClassRef,?>> extendsList =  new LinkedHashSet<VisitableBuilder<? extends ClassRef,?>>();
    private Set<VisitableBuilder<? extends ClassRef,?>> implementsList =  new LinkedHashSet<VisitableBuilder<? extends ClassRef,?>>();
    private VisitableBuilder<? extends TypeDef,?> outerType;
    private Set<VisitableBuilder<? extends TypeDef,?>> innerTypes =  new LinkedHashSet<VisitableBuilder<? extends TypeDef,?>>();

    public AbstractTypeDefFluentImpl(){
    }
    public AbstractTypeDefFluentImpl(AbstractTypeDef<?,?> instance){
            this.withKind(instance.getKind()); 
            this.withPackageName(instance.getPackageName()); 
            this.withName(instance.getName()); 
            this.withAnnotations(instance.getAnnotations()); 
            this.withExtendsList(instance.getExtendsList()); 
            this.withImplementsList(instance.getImplementsList()); 
            this.withOuterType(instance.getOuterType()); 
            this.withInnerTypes(instance.getInnerTypes()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public Kind getKind(){
            return this.kind;
    }

    public A withKind(Kind kind){
            this.kind=kind; return (A) this;
    }

    public String getPackageName(){
            return this.packageName;
    }

    public A withPackageName(String packageName){
            this.packageName=packageName; return (A) this;
    }

    public String getName(){
            return this.name;
    }

    public A withName(String name){
            this.name=name; return (A) this;
    }

    public A addToAnnotations(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A removeFromAnnotations(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.annotations.remove(builder);} return (A)this;
    }

    public Set<ClassRef> getAnnotations(){
            return build(annotations);
    }

    public A withAnnotations(Set<ClassRef> annotations){
            this.annotations.clear();
            if (annotations != null) {for (ClassRef item : annotations){this.addToAnnotations(item);}} return (A) this;
    }

    public A withAnnotations(ClassRef... annotations){
            this.annotations.clear(); if (annotations != null) {for (ClassRef item :annotations){ this.addToAnnotations(item);}} return (A) this;
    }

    public AbstractTypeDefFluent.AnnotationsNested<A> addNewAnnotation(){
            return new AnnotationsNestedImpl();
    }

    public AbstractTypeDefFluent.AnnotationsNested<A> addNewAnnotationLike(ClassRef item){
            return new AnnotationsNestedImpl(item);
    }

    public A addToExtendsList(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.extendsList.add(builder);} return (A)this;
    }

    public A removeFromExtendsList(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.extendsList.remove(builder);} return (A)this;
    }

    public Set<ClassRef> getExtendsList(){
            return build(extendsList);
    }

    public A withExtendsList(Set<ClassRef> extendsList){
            this.extendsList.clear();
            if (extendsList != null) {for (ClassRef item : extendsList){this.addToExtendsList(item);}} return (A) this;
    }

    public A withExtendsList(ClassRef... extendsList){
            this.extendsList.clear(); if (extendsList != null) {for (ClassRef item :extendsList){ this.addToExtendsList(item);}} return (A) this;
    }

    public AbstractTypeDefFluent.ExtendsListNested<A> addNewExtendsList(){
            return new ExtendsListNestedImpl();
    }

    public AbstractTypeDefFluent.ExtendsListNested<A> addNewExtendsListLike(ClassRef item){
            return new ExtendsListNestedImpl(item);
    }

    public A addToImplementsList(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.implementsList.add(builder);} return (A)this;
    }

    public A removeFromImplementsList(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.implementsList.remove(builder);} return (A)this;
    }

    public Set<ClassRef> getImplementsList(){
            return build(implementsList);
    }

    public A withImplementsList(Set<ClassRef> implementsList){
            this.implementsList.clear();
            if (implementsList != null) {for (ClassRef item : implementsList){this.addToImplementsList(item);}} return (A) this;
    }

    public A withImplementsList(ClassRef... implementsList){
            this.implementsList.clear(); if (implementsList != null) {for (ClassRef item :implementsList){ this.addToImplementsList(item);}} return (A) this;
    }

    public AbstractTypeDefFluent.ImplementsListNested<A> addNewImplementsList(){
            return new ImplementsListNestedImpl();
    }

    public AbstractTypeDefFluent.ImplementsListNested<A> addNewImplementsListLike(ClassRef item){
            return new ImplementsListNestedImpl(item);
    }

    public TypeDef getOuterType(){
            return this.outerType!=null?this.outerType.build():null;
    }

    public A withOuterType(TypeDef outerType){
            if (outerType instanceof ClassDef){ this.outerType= new ClassDefBuilder((ClassDef)outerType); _visitables.add(this.outerType);}
            if (outerType instanceof EnumDef){ this.outerType= new EnumDefBuilder((EnumDef)outerType); _visitables.add(this.outerType);}
            return (A) this;
    }

    public A addToInnerTypes(TypeDef... items){
            for (TypeDef item : items) {if (item instanceof ClassDef){addToClassDefInnerTypes((ClassDef)item);}
 else if (item instanceof EnumDef){addToEnumDefInnerTypes((EnumDef)item);}
} return (A)this;
    }

    public A removeFromInnerTypes(TypeDef... items){
            for (TypeDef item : items) {if (item instanceof ClassDef){removeFromClassDefInnerTypes((ClassDef)item);}
 else if (item instanceof EnumDef){removeFromEnumDefInnerTypes((EnumDef)item);}
} return (A)this;
    }

    public Set<TypeDef> getInnerTypes(){
            return build(innerTypes);
    }

    public A withInnerTypes(Set<TypeDef> innerTypes){
            this.innerTypes.clear();
            if (innerTypes != null) {for (TypeDef item : innerTypes){this.addToInnerTypes(item);}} return (A) this;
    }

    public A withInnerTypes(TypeDef... innerTypes){
            this.innerTypes.clear(); if (innerTypes != null) {for (TypeDef item :innerTypes){ this.addToInnerTypes(item);}} return (A) this;
    }

    public A addToClassDefInnerTypes(ClassDef... items){
            for (ClassDef item : items) {ClassDefBuilder builder = new ClassDefBuilder(item);_visitables.add(builder);this.innerTypes.add(builder);} return (A)this;
    }

    public A removeFromClassDefInnerTypes(ClassDef... items){
            for (ClassDef item : items) {ClassDefBuilder builder = new ClassDefBuilder(item);_visitables.remove(builder);this.innerTypes.remove(builder);} return (A)this;
    }

    public AbstractTypeDefFluent.ClassDefInnerTypesNested<A> addNewClassDefInnerType(){
            return new ClassDefInnerTypesNestedImpl();
    }

    public AbstractTypeDefFluent.ClassDefInnerTypesNested<A> addNewClassDefInnerTypeLike(ClassDef item){
            return new ClassDefInnerTypesNestedImpl(item);
    }

    public A addToEnumDefInnerTypes(EnumDef... items){
            for (EnumDef item : items) {EnumDefBuilder builder = new EnumDefBuilder(item);_visitables.add(builder);this.innerTypes.add(builder);} return (A)this;
    }

    public A removeFromEnumDefInnerTypes(EnumDef... items){
            for (EnumDef item : items) {EnumDefBuilder builder = new EnumDefBuilder(item);_visitables.remove(builder);this.innerTypes.remove(builder);} return (A)this;
    }

    public AbstractTypeDefFluent.EnumDefInnerTypesNested<A> addNewEnumDefInnerType(){
            return new EnumDefInnerTypesNestedImpl();
    }

    public AbstractTypeDefFluent.EnumDefInnerTypesNested<A> addNewEnumDefInnerTypeLike(EnumDef item){
            return new EnumDefInnerTypesNestedImpl(item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            AbstractTypeDefFluentImpl that = (AbstractTypeDefFluentImpl) o;
            if (kind != null ? !kind.equals(that.kind) :that.kind != null) return false;
            if (packageName != null ? !packageName.equals(that.packageName) :that.packageName != null) return false;
            if (name != null ? !name.equals(that.name) :that.name != null) return false;
            if (annotations != null ? !annotations.equals(that.annotations) :that.annotations != null) return false;
            if (extendsList != null ? !extendsList.equals(that.extendsList) :that.extendsList != null) return false;
            if (implementsList != null ? !implementsList.equals(that.implementsList) :that.implementsList != null) return false;
            if (outerType != null ? !outerType.equals(that.outerType) :that.outerType != null) return false;
            if (innerTypes != null ? !innerTypes.equals(that.innerTypes) :that.innerTypes != null) return false;
            return true;
    }


    public class AnnotationsNestedImpl<N> extends ClassRefFluentImpl<AnnotationsNested<N>> implements AbstractTypeDefFluent.AnnotationsNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            AnnotationsNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
            AnnotationsNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
    
    public N endAnnotation(){
            return and();
    }
    public N and(){
            return (N) AbstractTypeDefFluentImpl.this.addToAnnotations(builder.build());
    }

}
    public class ExtendsListNestedImpl<N> extends ClassRefFluentImpl<ExtendsListNested<N>> implements AbstractTypeDefFluent.ExtendsListNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            ExtendsListNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
            ExtendsListNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
    
    public N endExtendsList(){
            return and();
    }
    public N and(){
            return (N) AbstractTypeDefFluentImpl.this.addToExtendsList(builder.build());
    }

}
    public class ImplementsListNestedImpl<N> extends ClassRefFluentImpl<ImplementsListNested<N>> implements AbstractTypeDefFluent.ImplementsListNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            ImplementsListNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
            ImplementsListNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
    
    public N endImplementsList(){
            return and();
    }
    public N and(){
            return (N) AbstractTypeDefFluentImpl.this.addToImplementsList(builder.build());
    }

}
    public class ClassDefInnerTypesNestedImpl<N> extends ClassDefFluentImpl<AbstractTypeDefFluent.ClassDefInnerTypesNested<N>> implements AbstractTypeDefFluent.ClassDefInnerTypesNested<N>,Nested<N>{

            private final ClassDefBuilder builder;
    
            ClassDefInnerTypesNestedImpl(){
                    this.builder = new ClassDefBuilder(this);
            }
            ClassDefInnerTypesNestedImpl(ClassDef item){
                    this.builder = new ClassDefBuilder(this, item);
            }
    
    public N endClassDefInnerType(){
            return and();
    }
    public N and(){
            return (N) AbstractTypeDefFluentImpl.this.addToClassDefInnerTypes(builder.build());
    }

}
    public class EnumDefInnerTypesNestedImpl<N> extends EnumDefFluentImpl<AbstractTypeDefFluent.EnumDefInnerTypesNested<N>> implements AbstractTypeDefFluent.EnumDefInnerTypesNested<N>,Nested<N>{

            private final EnumDefBuilder builder;
    
            EnumDefInnerTypesNestedImpl(){
                    this.builder = new EnumDefBuilder(this);
            }
            EnumDefInnerTypesNestedImpl(EnumDef item){
                    this.builder = new EnumDefBuilder(this, item);
            }
    
    public N endEnumDefInnerType(){
            return and();
    }
    public N and(){
            return (N) AbstractTypeDefFluentImpl.this.addToEnumDefInnerTypes(builder.build());
    }

}


}
