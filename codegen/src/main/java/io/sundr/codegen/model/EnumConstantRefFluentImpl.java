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
import io.sundr.builder.VisitableBuilder;
import io.sundr.codegen.model.ClassDef;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.ClassRefFluentImpl;
import io.sundr.codegen.model.EnumConstantRef;
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
import io.sundr.codegen.model.WildcardRefFluentImpl;

import java.util.ArrayList;
import java.util.List;

public class EnumConstantRefFluentImpl<A extends EnumConstantRefFluent<A>> extends io.sundr.codegen.model.AbstractTypeRefFluentImpl<A> implements EnumConstantRefFluent<A>{

    private String name;
    private List<VisitableBuilder<? extends TypeRef,?>> arguments =  new ArrayList<VisitableBuilder<? extends TypeRef,?>>();
    private VisitableBuilder<? extends ClassDef,?> body;

    public EnumConstantRefFluentImpl(){
    }
    public EnumConstantRefFluentImpl(EnumConstantRef instance){
            this.withName(instance.getName()); 
            this.withArguments(instance.getArguments()); 
            this.withBody(instance.getBody()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public String getName(){
            return this.name;
    }

    public A withName(String name){
            this.name=name; return (A) this;
    }

    public A addToArguments(TypeRef... items){
            for (TypeRef item : items) {if (item instanceof VoidRef){addToVoidRefArguments((VoidRef)item);}
 else if (item instanceof EnumConstantRef){addToEnumConstantRefArguments((EnumConstantRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefArguments((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){addToPrimitiveRefArguments((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefArguments((TypeParamRef)item);}
 else if (item instanceof ClassRef){addToClassRefArguments((ClassRef)item);}
} return (A)this;
    }

    public A removeFromArguments(TypeRef... items){
            for (TypeRef item : items) {if (item instanceof VoidRef){removeFromVoidRefArguments((VoidRef)item);}
 else if (item instanceof EnumConstantRef){removeFromEnumConstantRefArguments((EnumConstantRef)item);}
 else if (item instanceof WildcardRef){removeFromWildcardRefArguments((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){removeFromPrimitiveRefArguments((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){removeFromTypeParamRefArguments((TypeParamRef)item);}
 else if (item instanceof ClassRef){removeFromClassRefArguments((ClassRef)item);}
} return (A)this;
    }

    public List<TypeRef> getArguments(){
            return build(arguments);
    }

    public A withArguments(List<TypeRef> arguments){
            this.arguments.clear();
            if (arguments != null) {for (TypeRef item : arguments){this.addToArguments(item);}} return (A) this;
    }

    public A withArguments(TypeRef... arguments){
            this.arguments.clear(); if (arguments != null) {for (TypeRef item :arguments){ this.addToArguments(item);}} return (A) this;
    }

    public A addToVoidRefArguments(VoidRef... items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromVoidRefArguments(VoidRef... items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public EnumConstantRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgument(){
            return new VoidRefArgumentsNestedImpl();
    }

    public EnumConstantRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(VoidRef item){
            return new VoidRefArgumentsNestedImpl(item);
    }

    public A addToEnumConstantRefArguments(EnumConstantRef... items){
            for (EnumConstantRef item : items) {EnumConstantRefBuilder builder = new EnumConstantRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromEnumConstantRefArguments(EnumConstantRef... items){
            for (EnumConstantRef item : items) {EnumConstantRefBuilder builder = new EnumConstantRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public EnumConstantRefFluent.EnumConstantRefArgumentsNested<A> addNewEnumConstantRefArgument(){
            return new EnumConstantRefArgumentsNestedImpl();
    }

    public EnumConstantRefFluent.EnumConstantRefArgumentsNested<A> addNewEnumConstantRefArgumentLike(EnumConstantRef item){
            return new EnumConstantRefArgumentsNestedImpl(item);
    }

    public A addToWildcardRefArguments(WildcardRef... items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromWildcardRefArguments(WildcardRef... items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public EnumConstantRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgument(){
            return new WildcardRefArgumentsNestedImpl();
    }

    public EnumConstantRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(WildcardRef item){
            return new WildcardRefArgumentsNestedImpl(item);
    }

    public A addToPrimitiveRefArguments(PrimitiveRef... items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromPrimitiveRefArguments(PrimitiveRef... items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public EnumConstantRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument(){
            return new PrimitiveRefArgumentsNestedImpl();
    }

    public EnumConstantRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(PrimitiveRef item){
            return new PrimitiveRefArgumentsNestedImpl(item);
    }

    public A addToTypeParamRefArguments(TypeParamRef... items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromTypeParamRefArguments(TypeParamRef... items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public EnumConstantRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument(){
            return new TypeParamRefArgumentsNestedImpl();
    }

    public EnumConstantRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item){
            return new TypeParamRefArgumentsNestedImpl(item);
    }

    public A addToClassRefArguments(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromClassRefArguments(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public EnumConstantRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgument(){
            return new ClassRefArgumentsNestedImpl();
    }

    public EnumConstantRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item){
            return new ClassRefArgumentsNestedImpl(item);
    }

    public ClassDef getBody(){
            return this.body!=null?this.body.build():null;
    }

    public A withBody(ClassDef body){
            if (body!=null){ this.body= new ClassDefBuilder(body); _visitables.add(this.body);} return (A) this;
    }

    public EnumConstantRefFluent.BodyNested<A> withNewBody(){
            return new BodyNestedImpl();
    }

    public EnumConstantRefFluent.BodyNested<A> withNewBodyLike(ClassDef item){
            return new BodyNestedImpl(item);
    }

    public EnumConstantRefFluent.BodyNested<A> editBody(){
            return withNewBodyLike(getBody());
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            EnumConstantRefFluentImpl that = (EnumConstantRefFluentImpl) o;
            if (name != null ? !name.equals(that.name) :that.name != null) return false;
            if (arguments != null ? !arguments.equals(that.arguments) :that.arguments != null) return false;
            if (body != null ? !body.equals(that.body) :that.body != null) return false;
            return true;
    }


    public class VoidRefArgumentsNestedImpl<N> extends VoidRefFluentImpl<VoidRefArgumentsNested<N>> implements EnumConstantRefFluent.VoidRefArgumentsNested<N>,Nested<N>{

            private final VoidRefBuilder builder;
    
            VoidRefArgumentsNestedImpl(){
                    this.builder = new VoidRefBuilder(this);
            }
            VoidRefArgumentsNestedImpl(VoidRef item){
                    this.builder = new VoidRefBuilder(this, item);
            }
    
    public N endVoidRefArgument(){
            return and();
    }
    public N and(){
            return (N) EnumConstantRefFluentImpl.this.addToVoidRefArguments(builder.build());
    }

}
    public class EnumConstantRefArgumentsNestedImpl<N> extends EnumConstantRefFluentImpl<EnumConstantRefFluent.EnumConstantRefArgumentsNested<N>> implements EnumConstantRefFluent.EnumConstantRefArgumentsNested<N>,Nested<N>{

            private final EnumConstantRefBuilder builder;
    
            EnumConstantRefArgumentsNestedImpl(){
                    this.builder = new EnumConstantRefBuilder(this);
            }
            EnumConstantRefArgumentsNestedImpl(EnumConstantRef item){
                    this.builder = new EnumConstantRefBuilder(this, item);
            }
    
    public N endEnumConstantRefArgument(){
            return and();
    }
    public N and(){
            return (N) EnumConstantRefFluentImpl.this.addToEnumConstantRefArguments(builder.build());
    }

}
    public class WildcardRefArgumentsNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefArgumentsNested<N>> implements EnumConstantRefFluent.WildcardRefArgumentsNested<N>,Nested<N>{

            private final WildcardRefBuilder builder;
    
            WildcardRefArgumentsNestedImpl(){
                    this.builder = new WildcardRefBuilder(this);
            }
            WildcardRefArgumentsNestedImpl(WildcardRef item){
                    this.builder = new WildcardRefBuilder(this, item);
            }
    
    public N endWildcardRefArgument(){
            return and();
    }
    public N and(){
            return (N) EnumConstantRefFluentImpl.this.addToWildcardRefArguments(builder.build());
    }

}
    public class PrimitiveRefArgumentsNestedImpl<N> extends PrimitiveRefFluentImpl<PrimitiveRefArgumentsNested<N>> implements EnumConstantRefFluent.PrimitiveRefArgumentsNested<N>,Nested<N>{

            private final PrimitiveRefBuilder builder;
    
            PrimitiveRefArgumentsNestedImpl(){
                    this.builder = new PrimitiveRefBuilder(this);
            }
            PrimitiveRefArgumentsNestedImpl(PrimitiveRef item){
                    this.builder = new PrimitiveRefBuilder(this, item);
            }
    
    public N endPrimitiveRefArgument(){
            return and();
    }
    public N and(){
            return (N) EnumConstantRefFluentImpl.this.addToPrimitiveRefArguments(builder.build());
    }

}
    public class TypeParamRefArgumentsNestedImpl<N> extends TypeParamRefFluentImpl<TypeParamRefArgumentsNested<N>> implements EnumConstantRefFluent.TypeParamRefArgumentsNested<N>,Nested<N>{

            private final TypeParamRefBuilder builder;
    
            TypeParamRefArgumentsNestedImpl(){
                    this.builder = new TypeParamRefBuilder(this);
            }
            TypeParamRefArgumentsNestedImpl(TypeParamRef item){
                    this.builder = new TypeParamRefBuilder(this, item);
            }
    
    public N endTypeParamRefArgument(){
            return and();
    }
    public N and(){
            return (N) EnumConstantRefFluentImpl.this.addToTypeParamRefArguments(builder.build());
    }

}
    public class ClassRefArgumentsNestedImpl<N> extends ClassRefFluentImpl<ClassRefArgumentsNested<N>> implements EnumConstantRefFluent.ClassRefArgumentsNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            ClassRefArgumentsNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
            ClassRefArgumentsNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
    
    public N endClassRefArgument(){
            return and();
    }
    public N and(){
            return (N) EnumConstantRefFluentImpl.this.addToClassRefArguments(builder.build());
    }

}
    public class BodyNestedImpl<N> extends ClassDefFluentImpl<EnumConstantRefFluent.BodyNested<N>> implements EnumConstantRefFluent.BodyNested<N>,Nested<N>{

            private final ClassDefBuilder builder;
    
            BodyNestedImpl(){
                    this.builder = new ClassDefBuilder(this);
            }
            BodyNestedImpl(ClassDef item){
                    this.builder = new ClassDefBuilder(this, item);
            }
    
    public N endBody(){
            return and();
    }
    public N and(){
            return (N) EnumConstantRefFluentImpl.this.withBody(builder.build());
    }

}


}
