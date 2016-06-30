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

import io.sundr.builder.Nested;
import io.sundr.codegen.model.*;
import io.sundr.codegen.model.AbstractTypeRefFluentImpl;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.ClassRefFluentImpl;
import io.sundr.codegen.model.PrimitiveRef;
import io.sundr.codegen.model.PrimitiveRefBuilder;
import io.sundr.codegen.model.PrimitiveRefFluentImpl;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeParamRefFluentImpl;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.model.VoidRef;
import io.sundr.codegen.model.VoidRefBuilder;
import io.sundr.codegen.model.VoidRefFluentImpl;
import io.sundr.codegen.model.WildcardRef;
import io.sundr.codegen.model.WildcardRefBuilder;
import io.sundr.codegen.model.WildcardRefFluent;


public class WildcardRefFluentImpl<A extends io.sundr.codegen.model.WildcardRefFluent<A>> extends io.sundr.codegen.model.AbstractTypeRefFluentImpl<A> implements io.sundr.codegen.model.WildcardRefFluent<A> {

    private java.util.List<io.sundr.codegen.model.TypeRef> bounds = new java.util.ArrayList<io.sundr.codegen.model.TypeRef>();    java.util.List<io.sundr.builder.VisitableBuilder<io.sundr.codegen.model.VoidRef,?>> voidRefBounds =  new java.util.ArrayList<io.sundr.builder.VisitableBuilder<io.sundr.codegen.model.VoidRef,?>>();    java.util.List<io.sundr.builder.VisitableBuilder<io.sundr.codegen.model.WildcardRef,?>> wildcardRefBounds =  new java.util.ArrayList<io.sundr.builder.VisitableBuilder<io.sundr.codegen.model.WildcardRef,?>>();    java.util.List<io.sundr.builder.VisitableBuilder<io.sundr.codegen.model.PrimitiveRef,?>> primitiveRefBounds =  new java.util.ArrayList<io.sundr.builder.VisitableBuilder<io.sundr.codegen.model.PrimitiveRef,?>>();    java.util.List<io.sundr.builder.VisitableBuilder<io.sundr.codegen.model.TypeParamRef,?>> typeParamRefBounds =  new java.util.ArrayList<io.sundr.builder.VisitableBuilder<io.sundr.codegen.model.TypeParamRef,?>>();    java.util.List<io.sundr.builder.VisitableBuilder<io.sundr.codegen.model.ClassRef,?>> classRefBounds =  new java.util.ArrayList<io.sundr.builder.VisitableBuilder<io.sundr.codegen.model.ClassRef,?>>();
    public WildcardRefFluentImpl(){
    }
    public WildcardRefFluentImpl(io.sundr.codegen.model.WildcardRef instance){
            this.withBounds(instance.getBounds());
            this.withAttributes(instance.getAttributes());
    }

    public A addToBounds(io.sundr.codegen.model.TypeRef... items){
            for (io.sundr.codegen.model.TypeRef item : items) {if (item instanceof io.sundr.codegen.model.VoidRef){addToVoidRefBounds((io.sundr.codegen.model.VoidRef)item);}
 else if (item instanceof io.sundr.codegen.model.WildcardRef){addToWildcardRefBounds((io.sundr.codegen.model.WildcardRef)item);}
 else if (item instanceof io.sundr.codegen.model.PrimitiveRef){addToPrimitiveRefBounds((io.sundr.codegen.model.PrimitiveRef)item);}
 else if (item instanceof io.sundr.codegen.model.TypeParamRef){addToTypeParamRefBounds((io.sundr.codegen.model.TypeParamRef)item);}
 else if (item instanceof io.sundr.codegen.model.ClassRef){addToClassRefBounds((io.sundr.codegen.model.ClassRef)item);}
} return (A)this;
        }
    public A removeFromBounds(io.sundr.codegen.model.TypeRef... items){
            for (io.sundr.codegen.model.TypeRef item : items) {if (item instanceof io.sundr.codegen.model.VoidRef){removeFromVoidRefBounds((io.sundr.codegen.model.VoidRef)item);}
 else if (item instanceof io.sundr.codegen.model.WildcardRef){removeFromWildcardRefBounds((io.sundr.codegen.model.WildcardRef)item);}
 else if (item instanceof io.sundr.codegen.model.PrimitiveRef){removeFromPrimitiveRefBounds((io.sundr.codegen.model.PrimitiveRef)item);}
 else if (item instanceof io.sundr.codegen.model.TypeParamRef){removeFromTypeParamRefBounds((io.sundr.codegen.model.TypeParamRef)item);}
 else if (item instanceof io.sundr.codegen.model.ClassRef){removeFromClassRefBounds((io.sundr.codegen.model.ClassRef)item);}
} return (A)this;
        }
    public java.util.List<io.sundr.codegen.model.TypeRef> getBounds(){
            return aggregate(this.<io.sundr.codegen.model.TypeRef>build(classRefBounds), this.<io.sundr.codegen.model.TypeRef>build(primitiveRefBounds), this.<io.sundr.codegen.model.TypeRef>build(typeParamRefBounds), this.<io.sundr.codegen.model.TypeRef>build(voidRefBounds), this.<io.sundr.codegen.model.TypeRef>build(wildcardRefBounds));
        }
    public A withBounds(java.util.List<io.sundr.codegen.model.TypeRef> bounds){
            this.bounds.clear();
            if (bounds != null) {for (io.sundr.codegen.model.TypeRef item : bounds){this.addToBounds(item);}} return (A) this;
        }
    public A withBounds(io.sundr.codegen.model.TypeRef[] bounds){
            this.bounds.clear(); if (bounds != null) {for (io.sundr.codegen.model.TypeRef item :bounds){ this.addToBounds(item);}} return (A) this;
        }
    public A addToVoidRefBounds(io.sundr.codegen.model.VoidRef... items){
            for (io.sundr.codegen.model.VoidRef item : items) {
                io.sundr.codegen.model.VoidRefBuilder builder = new io.sundr.codegen.model.VoidRefBuilder(item);_visitables.add(builder);this.voidRefBounds.add(builder);} return (A)this;
        }
    public A removeFromVoidRefBounds(io.sundr.codegen.model.VoidRef... items){
            for (io.sundr.codegen.model.VoidRef item : items) {
                io.sundr.codegen.model.VoidRefBuilder builder = new io.sundr.codegen.model.VoidRefBuilder(item);_visitables.remove(builder);this.voidRefBounds.remove(builder);} return (A)this;
        }
    public VoidRefBoundsNested<A> addNewVoidRefBound(){
            return new VoidRefBoundsNestedImpl();
        }
    public VoidRefBoundsNested<A> addNewVoidRefBoundLike(io.sundr.codegen.model.VoidRef item){
            return new VoidRefBoundsNestedImpl(item);
        }
    public A addToWildcardRefBounds(io.sundr.codegen.model.WildcardRef... items){
            for (io.sundr.codegen.model.WildcardRef item : items) {
                io.sundr.codegen.model.WildcardRefBuilder builder = new io.sundr.codegen.model.WildcardRefBuilder(item);_visitables.add(builder);this.wildcardRefBounds.add(builder);} return (A)this;
        }
    public A removeFromWildcardRefBounds(io.sundr.codegen.model.WildcardRef... items){
            for (io.sundr.codegen.model.WildcardRef item : items) {
                io.sundr.codegen.model.WildcardRefBuilder builder = new io.sundr.codegen.model.WildcardRefBuilder(item);_visitables.remove(builder);this.wildcardRefBounds.remove(builder);} return (A)this;
        }
    public WildcardRefBoundsNested<A> addNewWildcardRefBound(){
            return new WildcardRefBoundsNestedImpl();
        }
    public WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(io.sundr.codegen.model.WildcardRef item){
            return new WildcardRefBoundsNestedImpl(item);
        }
    public A addToPrimitiveRefBounds(io.sundr.codegen.model.PrimitiveRef... items){
            for (io.sundr.codegen.model.PrimitiveRef item : items) {
                io.sundr.codegen.model.PrimitiveRefBuilder builder = new io.sundr.codegen.model.PrimitiveRefBuilder(item);_visitables.add(builder);this.primitiveRefBounds.add(builder);} return (A)this;
        }
    public A removeFromPrimitiveRefBounds(io.sundr.codegen.model.PrimitiveRef... items){
            for (io.sundr.codegen.model.PrimitiveRef item : items) {
                io.sundr.codegen.model.PrimitiveRefBuilder builder = new io.sundr.codegen.model.PrimitiveRefBuilder(item);_visitables.remove(builder);this.primitiveRefBounds.remove(builder);} return (A)this;
        }
    public PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound(){
            return new PrimitiveRefBoundsNestedImpl();
        }
    public PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(io.sundr.codegen.model.PrimitiveRef item){
            return new PrimitiveRefBoundsNestedImpl(item);
        }
    public A addToTypeParamRefBounds(io.sundr.codegen.model.TypeParamRef... items){
            for (io.sundr.codegen.model.TypeParamRef item : items) {
                io.sundr.codegen.model.TypeParamRefBuilder builder = new io.sundr.codegen.model.TypeParamRefBuilder(item);_visitables.add(builder);this.typeParamRefBounds.add(builder);} return (A)this;
        }
    public A removeFromTypeParamRefBounds(io.sundr.codegen.model.TypeParamRef... items){
            for (io.sundr.codegen.model.TypeParamRef item : items) {
                io.sundr.codegen.model.TypeParamRefBuilder builder = new io.sundr.codegen.model.TypeParamRefBuilder(item);_visitables.remove(builder);this.typeParamRefBounds.remove(builder);} return (A)this;
        }
    public TypeParamRefBoundsNested<A> addNewTypeParamRefBound(){
            return new TypeParamRefBoundsNestedImpl();
        }
    public TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(io.sundr.codegen.model.TypeParamRef item){
            return new TypeParamRefBoundsNestedImpl(item);
        }
    public A addToClassRefBounds(io.sundr.codegen.model.ClassRef... items){
            for (io.sundr.codegen.model.ClassRef item : items) {
                io.sundr.codegen.model.ClassRefBuilder builder = new io.sundr.codegen.model.ClassRefBuilder(item);_visitables.add(builder);this.classRefBounds.add(builder);} return (A)this;
        }
    public A removeFromClassRefBounds(io.sundr.codegen.model.ClassRef... items){
            for (io.sundr.codegen.model.ClassRef item : items) {
                io.sundr.codegen.model.ClassRefBuilder builder = new io.sundr.codegen.model.ClassRefBuilder(item);_visitables.remove(builder);this.classRefBounds.remove(builder);} return (A)this;
        }
    public ClassRefBoundsNested<A> addNewClassRefBound(){
            return new ClassRefBoundsNestedImpl();
        }
    public ClassRefBoundsNested<A> addNewClassRefBoundLike(io.sundr.codegen.model.ClassRef item){
            return new ClassRefBoundsNestedImpl(item);
        }
    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            WildcardRefFluentImpl that = (WildcardRefFluentImpl) o;
            if (bounds != null ? !bounds.equals(that.bounds) :that.bounds != null) return false;
            if (voidRefBounds != null ? !voidRefBounds.equals(that.voidRefBounds) :that.voidRefBounds != null) return false;
            if (wildcardRefBounds != null ? !wildcardRefBounds.equals(that.wildcardRefBounds) :that.wildcardRefBounds != null) return false;
            if (primitiveRefBounds != null ? !primitiveRefBounds.equals(that.primitiveRefBounds) :that.primitiveRefBounds != null) return false;
            if (typeParamRefBounds != null ? !typeParamRefBounds.equals(that.typeParamRefBounds) :that.typeParamRefBounds != null) return false;
            if (classRefBounds != null ? !classRefBounds.equals(that.classRefBounds) :that.classRefBounds != null) return false;
            return true;
        }

    public class VoidRefBoundsNestedImpl<N> extends io.sundr.codegen.model.VoidRefFluentImpl<VoidRefBoundsNested<N>> implements VoidRefBoundsNested<N>,Nested<N>{

        private final io.sundr.codegen.model.VoidRefBuilder builder;

            VoidRefBoundsNestedImpl(){
                    this.builder = new io.sundr.codegen.model.VoidRefBuilder(this);
                }
            VoidRefBoundsNestedImpl(io.sundr.codegen.model.VoidRef item){
                    this.builder = new io.sundr.codegen.model.VoidRefBuilder(this, item);
                }

            public N endVoidRefBound(){
                    return and();
                }
            public N and(){
                    return (N) WildcardRefFluentImpl.this.addToVoidRefBounds(builder.build());
                }

}
    public class WildcardRefBoundsNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefBoundsNested<N>> implements WildcardRefBoundsNested<N>,Nested<N>{

        private final io.sundr.codegen.model.WildcardRefBuilder builder;

            WildcardRefBoundsNestedImpl(){
                    this.builder = new io.sundr.codegen.model.WildcardRefBuilder(this);
                }
            WildcardRefBoundsNestedImpl(io.sundr.codegen.model.WildcardRef item){
                    this.builder = new io.sundr.codegen.model.WildcardRefBuilder(this, item);
                }

            public N endWildcardRefBound(){
                    return and();
                }
            public N and(){
                    return (N) WildcardRefFluentImpl.this.addToWildcardRefBounds(builder.build());
                }

}
    public class PrimitiveRefBoundsNestedImpl<N> extends io.sundr.codegen.model.PrimitiveRefFluentImpl<PrimitiveRefBoundsNested<N>> implements PrimitiveRefBoundsNested<N>,Nested<N>{

        private final io.sundr.codegen.model.PrimitiveRefBuilder builder;

            PrimitiveRefBoundsNestedImpl(){
                    this.builder = new io.sundr.codegen.model.PrimitiveRefBuilder(this);
                }
            PrimitiveRefBoundsNestedImpl(io.sundr.codegen.model.PrimitiveRef item){
                    this.builder = new io.sundr.codegen.model.PrimitiveRefBuilder(this, item);
                }

            public N endPrimitiveRefBound(){
                    return and();
                }
            public N and(){
                    return (N) WildcardRefFluentImpl.this.addToPrimitiveRefBounds(builder.build());
                }

}
    public class TypeParamRefBoundsNestedImpl<N> extends io.sundr.codegen.model.TypeParamRefFluentImpl<TypeParamRefBoundsNested<N>> implements TypeParamRefBoundsNested<N>,Nested<N>{

        private final io.sundr.codegen.model.TypeParamRefBuilder builder;

            TypeParamRefBoundsNestedImpl(){
                    this.builder = new io.sundr.codegen.model.TypeParamRefBuilder(this);
                }
            TypeParamRefBoundsNestedImpl(io.sundr.codegen.model.TypeParamRef item){
                    this.builder = new io.sundr.codegen.model.TypeParamRefBuilder(this, item);
                }

            public N endTypeParamRefBound(){
                    return and();
                }
            public N and(){
                    return (N) WildcardRefFluentImpl.this.addToTypeParamRefBounds(builder.build());
                }

}
    class ClassRefBoundsNestedImpl<N> extends io.sundr.codegen.model.ClassRefFluentImpl<ClassRefBoundsNested<N>> implements ClassRefBoundsNested<N>,Nested<N>{

        private final io.sundr.codegen.model.ClassRefBuilder builder;

            ClassRefBoundsNestedImpl(){
                    this.builder = new io.sundr.codegen.model.ClassRefBuilder(this);
                }
            ClassRefBoundsNestedImpl(io.sundr.codegen.model.ClassRef item){
                    this.builder = new io.sundr.codegen.model.ClassRefBuilder(this, item);
                }
    
            public N endClassRefBound(){
                    return and();
                }
            public N and(){
                    return (N) WildcardRefFluentImpl.this.addToClassRefBounds(builder.build());
                }
    
}


}
