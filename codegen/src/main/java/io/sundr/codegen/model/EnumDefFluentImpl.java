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
import io.sundr.codegen.model.EnumDef;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.MethodFluentImpl;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.PropertyFluentImpl;

import java.util.LinkedHashSet;
import java.util.Set;

public class EnumDefFluentImpl<A extends EnumDefFluent<A>> extends AbstractTypeDefFluentImpl<io.sundr.codegen.model.EnumDef,EnumDefBuilder,A> implements EnumDefFluent<A>{

    private Set<VisitableBuilder<? extends Property,?>> properties =  new LinkedHashSet<VisitableBuilder<? extends Property,?>>();
    private Set<VisitableBuilder<? extends Method,?>> constructors =  new LinkedHashSet<VisitableBuilder<? extends Method,?>>();
    private Set<VisitableBuilder<? extends Method,?>> methods =  new LinkedHashSet<VisitableBuilder<? extends Method,?>>();

    public EnumDefFluentImpl(){
    }
    public EnumDefFluentImpl(EnumDef instance){
            this.withPackageName(instance.getPackageName()); 
            this.withName(instance.getName()); 
            this.withAnnotations(instance.getAnnotations()); 
            this.withImplementsList(instance.getImplementsList()); 
            this.withInnerTypes(instance.getInnerTypes()); 
            this.withOuterType(instance.getOuterType()); 
            this.withProperties(instance.getProperties()); 
            this.withConstructors(instance.getConstructors()); 
            this.withMethods(instance.getMethods()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public A addToProperties(Property... items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.add(builder);this.properties.add(builder);} return (A)this;
    }

    public A removeFromProperties(Property... items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.remove(builder);this.properties.remove(builder);} return (A)this;
    }

    public Set<Property> getProperties(){
            return build(properties);
    }

    public A withProperties(Set<Property> properties){
            this.properties.clear();
            if (properties != null) {for (Property item : properties){this.addToProperties(item);}} return (A) this;
    }

    public A withProperties(Property... properties){
            this.properties.clear(); if (properties != null) {for (Property item :properties){ this.addToProperties(item);}} return (A) this;
    }

    public EnumDefFluent.PropertiesNested<A> addNewProperty(){
            return new PropertiesNestedImpl();
    }

    public EnumDefFluent.PropertiesNested<A> addNewPropertyLike(Property item){
            return new PropertiesNestedImpl(item);
    }

    public A addToConstructors(Method... items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.constructors.add(builder);} return (A)this;
    }

    public A removeFromConstructors(Method... items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.remove(builder);this.constructors.remove(builder);} return (A)this;
    }

    public Set<Method> getConstructors(){
            return build(constructors);
    }

    public A withConstructors(Set<Method> constructors){
            this.constructors.clear();
            if (constructors != null) {for (Method item : constructors){this.addToConstructors(item);}} return (A) this;
    }

    public A withConstructors(Method... constructors){
            this.constructors.clear(); if (constructors != null) {for (Method item :constructors){ this.addToConstructors(item);}} return (A) this;
    }

    public EnumDefFluent.ConstructorsNested<A> addNewConstructor(){
            return new ConstructorsNestedImpl();
    }

    public EnumDefFluent.ConstructorsNested<A> addNewConstructorLike(Method item){
            return new ConstructorsNestedImpl(item);
    }

    public A addToMethods(Method... items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.methods.add(builder);} return (A)this;
    }

    public A removeFromMethods(Method... items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.remove(builder);this.methods.remove(builder);} return (A)this;
    }

    public Set<Method> getMethods(){
            return build(methods);
    }

    public A withMethods(Set<Method> methods){
            this.methods.clear();
            if (methods != null) {for (Method item : methods){this.addToMethods(item);}} return (A) this;
    }

    public A withMethods(Method... methods){
            this.methods.clear(); if (methods != null) {for (Method item :methods){ this.addToMethods(item);}} return (A) this;
    }

    public EnumDefFluent.MethodsNested<A> addNewMethod(){
            return new MethodsNestedImpl();
    }

    public EnumDefFluent.MethodsNested<A> addNewMethodLike(Method item){
            return new MethodsNestedImpl(item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            EnumDefFluentImpl that = (EnumDefFluentImpl) o;
            if (properties != null ? !properties.equals(that.properties) :that.properties != null) return false;
            if (constructors != null ? !constructors.equals(that.constructors) :that.constructors != null) return false;
            if (methods != null ? !methods.equals(that.methods) :that.methods != null) return false;
            return true;
    }


    public class PropertiesNestedImpl<N> extends PropertyFluentImpl<PropertiesNested<N>> implements EnumDefFluent.PropertiesNested<N>,Nested<N>{

            private final PropertyBuilder builder;
    
            PropertiesNestedImpl(){
                    this.builder = new PropertyBuilder(this);
            }
            PropertiesNestedImpl(Property item){
                    this.builder = new PropertyBuilder(this, item);
            }
    
    public N endProperty(){
            return and();
    }
    public N and(){
            return (N) EnumDefFluentImpl.this.addToProperties(builder.build());
    }

}
    public class ConstructorsNestedImpl<N> extends MethodFluentImpl<ConstructorsNested<N>> implements EnumDefFluent.ConstructorsNested<N>,Nested<N>{

            private final MethodBuilder builder;
    
            ConstructorsNestedImpl(){
                    this.builder = new MethodBuilder(this);
            }
            ConstructorsNestedImpl(Method item){
                    this.builder = new MethodBuilder(this, item);
            }
    
    public N endConstructor(){
            return and();
    }
    public N and(){
            return (N) EnumDefFluentImpl.this.addToConstructors(builder.build());
    }

}
    public class MethodsNestedImpl<N> extends MethodFluentImpl<MethodsNested<N>> implements EnumDefFluent.MethodsNested<N>,Nested<N>{

            private final MethodBuilder builder;
    
            MethodsNestedImpl(){
                    this.builder = new MethodBuilder(this);
            }
            MethodsNestedImpl(Method item){
                    this.builder = new MethodBuilder(this, item);
            }
    
    public N endMethod(){
            return and();
    }
    public N and(){
            return (N) EnumDefFluentImpl.this.addToMethods(builder.build());
    }

}


}
