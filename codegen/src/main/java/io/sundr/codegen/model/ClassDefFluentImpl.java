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
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.MethodFluentImpl;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.PropertyFluentImpl;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamDefFluentImpl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ClassDefFluentImpl<A extends ClassDefFluent<A>> extends AbstractTypeDefFluentImpl<io.sundr.codegen.model.ClassDef,ClassDefBuilder,A> implements ClassDefFluent<A>{

    private List<VisitableBuilder<? extends TypeParamDef,?>> parameters =  new ArrayList<VisitableBuilder<? extends TypeParamDef,?>>();
    private Set<VisitableBuilder<? extends Property,?>> properties =  new LinkedHashSet<VisitableBuilder<? extends Property,?>>();
    private Set<VisitableBuilder<? extends Method,?>> constructors =  new LinkedHashSet<VisitableBuilder<? extends Method,?>>();
    private Set<VisitableBuilder<? extends Method,?>> methods =  new LinkedHashSet<VisitableBuilder<? extends Method,?>>();

    public ClassDefFluentImpl(){
    }
    public ClassDefFluentImpl(ClassDef instance){
            this.withKind(instance.getKind()); 
            this.withPackageName(instance.getPackageName()); 
            this.withName(instance.getName()); 
            this.withAnnotations(instance.getAnnotations()); 
            this.withExtendsList(instance.getExtendsList()); 
            this.withImplementsList(instance.getImplementsList()); 
            this.withParameters(instance.getParameters()); 
            this.withProperties(instance.getProperties()); 
            this.withConstructors(instance.getConstructors()); 
            this.withMethods(instance.getMethods()); 
            this.withOuterType(instance.getOuterType()); 
            this.withInnerTypes(instance.getInnerTypes()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public A addToParameters(TypeParamDef... items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder);} return (A)this;
    }

    public A removeFromParameters(TypeParamDef... items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.remove(builder);this.parameters.remove(builder);} return (A)this;
    }

    public List<TypeParamDef> getParameters(){
            return build(parameters);
    }

    public A withParameters(List<TypeParamDef> parameters){
            this.parameters.clear();
            if (parameters != null) {for (TypeParamDef item : parameters){this.addToParameters(item);}} return (A) this;
    }

    public A withParameters(TypeParamDef... parameters){
            this.parameters.clear(); if (parameters != null) {for (TypeParamDef item :parameters){ this.addToParameters(item);}} return (A) this;
    }

    public ClassDefFluent.ParametersNested<A> addNewParameter(){
            return new ParametersNestedImpl();
    }

    public ClassDefFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item){
            return new ParametersNestedImpl(item);
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

    public ClassDefFluent.PropertiesNested<A> addNewProperty(){
            return new PropertiesNestedImpl();
    }

    public ClassDefFluent.PropertiesNested<A> addNewPropertyLike(Property item){
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

    public ClassDefFluent.ConstructorsNested<A> addNewConstructor(){
            return new ConstructorsNestedImpl();
    }

    public ClassDefFluent.ConstructorsNested<A> addNewConstructorLike(Method item){
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

    public ClassDefFluent.MethodsNested<A> addNewMethod(){
            return new MethodsNestedImpl();
    }

    public ClassDefFluent.MethodsNested<A> addNewMethodLike(Method item){
            return new MethodsNestedImpl(item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            ClassDefFluentImpl that = (ClassDefFluentImpl) o;
            if (parameters != null ? !parameters.equals(that.parameters) :that.parameters != null) return false;
            if (properties != null ? !properties.equals(that.properties) :that.properties != null) return false;
            if (constructors != null ? !constructors.equals(that.constructors) :that.constructors != null) return false;
            if (methods != null ? !methods.equals(that.methods) :that.methods != null) return false;
            return true;
    }


    public class ParametersNestedImpl<N> extends TypeParamDefFluentImpl<ParametersNested<N>> implements ClassDefFluent.ParametersNested<N>,Nested<N>{

            private final TypeParamDefBuilder builder;
    
            ParametersNestedImpl(){
                    this.builder = new TypeParamDefBuilder(this);
            }
            ParametersNestedImpl(TypeParamDef item){
                    this.builder = new TypeParamDefBuilder(this, item);
            }
    
    public N endParameter(){
            return and();
    }
    public N and(){
            return (N) ClassDefFluentImpl.this.addToParameters(builder.build());
    }

}
    public class PropertiesNestedImpl<N> extends PropertyFluentImpl<PropertiesNested<N>> implements ClassDefFluent.PropertiesNested<N>,Nested<N>{

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
            return (N) ClassDefFluentImpl.this.addToProperties(builder.build());
    }

}
    public class ConstructorsNestedImpl<N> extends MethodFluentImpl<ConstructorsNested<N>> implements ClassDefFluent.ConstructorsNested<N>,Nested<N>{

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
            return (N) ClassDefFluentImpl.this.addToConstructors(builder.build());
    }

}
    public class MethodsNestedImpl<N> extends MethodFluentImpl<MethodsNested<N>> implements ClassDefFluent.MethodsNested<N>,Nested<N>{

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
            return (N) ClassDefFluentImpl.this.addToMethods(builder.build());
    }

}


}
