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

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

import java.util.ArrayList;
import java.util.List;

public class SourceFluentImpl<A extends SourceFluent<A>> extends BaseFluent<A> implements SourceFluent<A>{

    private List<VisitableBuilder<? extends TypeDef,?>> types =  new ArrayList<VisitableBuilder<? extends TypeDef,?>>();

    public SourceFluentImpl(){
    }
    public SourceFluentImpl(Source instance){
            this.withTypes(instance.getTypes()); 
    }

    public A addToTypes(TypeDef... items){
            for (TypeDef item : items) {if (item instanceof ClassDef){addToClassDefTypes((ClassDef)item);}
 else if (item instanceof EnumDef){addToEnumDefTypes((EnumDef)item);}
} return (A)this;
    }

    public A removeFromTypes(TypeDef... items){
            for (TypeDef item : items) {if (item instanceof ClassDef){removeFromClassDefTypes((ClassDef)item);}
 else if (item instanceof EnumDef){removeFromEnumDefTypes((EnumDef)item);}
} return (A)this;
    }

    public List<TypeDef> getTypes(){
            return build(types);
    }

    public A withTypes(List<TypeDef> types){
            this.types.clear();
            if (types != null) {for (TypeDef item : types){this.addToTypes(item);}} return (A) this;
    }

    public A withTypes(TypeDef... types){
            this.types.clear(); if (types != null) {for (TypeDef item :types){ this.addToTypes(item);}} return (A) this;
    }

    public A addToClassDefTypes(ClassDef... items){
            for (ClassDef item : items) {ClassDefBuilder builder = new ClassDefBuilder(item);_visitables.add(builder);this.types.add(builder);} return (A)this;
    }

    public A removeFromClassDefTypes(ClassDef... items){
            for (ClassDef item : items) {ClassDefBuilder builder = new ClassDefBuilder(item);_visitables.remove(builder);this.types.remove(builder);} return (A)this;
    }

    public SourceFluent.ClassDefTypesNested<A> addNewClassDefType(){
            return new ClassDefTypesNestedImpl();
    }

    public SourceFluent.ClassDefTypesNested<A> addNewClassDefTypeLike(ClassDef item){
            return new ClassDefTypesNestedImpl(item);
    }

    public A addToEnumDefTypes(EnumDef... items){
            for (EnumDef item : items) {EnumDefBuilder builder = new EnumDefBuilder(item);_visitables.add(builder);this.types.add(builder);} return (A)this;
    }

    public A removeFromEnumDefTypes(EnumDef... items){
            for (EnumDef item : items) {EnumDefBuilder builder = new EnumDefBuilder(item);_visitables.remove(builder);this.types.remove(builder);} return (A)this;
    }

    public SourceFluent.EnumDefTypesNested<A> addNewEnumDefType(){
            return new EnumDefTypesNestedImpl();
    }

    public SourceFluent.EnumDefTypesNested<A> addNewEnumDefTypeLike(EnumDef item){
            return new EnumDefTypesNestedImpl(item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SourceFluentImpl that = (SourceFluentImpl) o;
            if (types != null ? !types.equals(that.types) :that.types != null) return false;
            return true;
    }


    public class ClassDefTypesNestedImpl<N> extends ClassDefFluentImpl<SourceFluent.ClassDefTypesNested<N>> implements SourceFluent.ClassDefTypesNested<N>,Nested<N>{

            private final ClassDefBuilder builder;
    
            ClassDefTypesNestedImpl(){
                    this.builder = new ClassDefBuilder(this);
            }
            ClassDefTypesNestedImpl(ClassDef item){
                    this.builder = new ClassDefBuilder(this, item);
            }
    
    public N endClassDefType(){
            return and();
    }
    public N and(){
            return (N) SourceFluentImpl.this.addToClassDefTypes(builder.build());
    }

}
    public class EnumDefTypesNestedImpl<N> extends EnumDefFluentImpl<SourceFluent.EnumDefTypesNested<N>> implements SourceFluent.EnumDefTypesNested<N>,Nested<N>{

            private final EnumDefBuilder builder;
    
            EnumDefTypesNestedImpl(){
                    this.builder = new EnumDefBuilder(this);
            }
            EnumDefTypesNestedImpl(EnumDef item){
                    this.builder = new EnumDefBuilder(this, item);
            }
    
    public N endEnumDefType(){
            return and();
    }
    public N and(){
            return (N) SourceFluentImpl.this.addToEnumDefTypes(builder.build());
    }

}


}
