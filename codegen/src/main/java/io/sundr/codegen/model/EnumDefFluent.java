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
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodFluent;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyFluent;

import java.util.Set;

public interface EnumDefFluent<A extends EnumDefFluent<A>> extends AbstractTypeDefFluent<io.sundr.codegen.model.EnumDef,EnumDefBuilder,A>{


    public A addToProperties(Property... items);
    public A removeFromProperties(Property... items);
    public Set<Property> getProperties();
    public A withProperties(Set<Property> properties);
    public A withProperties(Property... properties);
    public EnumDefFluent.PropertiesNested<A> addNewProperty();
    public EnumDefFluent.PropertiesNested<A> addNewPropertyLike(Property item);
    public A addToConstructors(Method... items);
    public A removeFromConstructors(Method... items);
    public Set<Method> getConstructors();
    public A withConstructors(Set<Method> constructors);
    public A withConstructors(Method... constructors);
    public EnumDefFluent.ConstructorsNested<A> addNewConstructor();
    public EnumDefFluent.ConstructorsNested<A> addNewConstructorLike(Method item);
    public A addToMethods(Method... items);
    public A removeFromMethods(Method... items);
    public Set<Method> getMethods();
    public A withMethods(Set<Method> methods);
    public A withMethods(Method... methods);
    public EnumDefFluent.MethodsNested<A> addNewMethod();
    public EnumDefFluent.MethodsNested<A> addNewMethodLike(Method item);

    public interface PropertiesNested<N> extends Nested<N>,PropertyFluent<PropertiesNested<N>> {


    public N endProperty();    public N and();
}
    public interface ConstructorsNested<N> extends Nested<N>,MethodFluent<ConstructorsNested<N>> {


    public N endConstructor();    public N and();
}
    public interface MethodsNested<N> extends Nested<N>,MethodFluent<MethodsNested<N>> {

        
    public N endMethod();    public N and();
}


}
