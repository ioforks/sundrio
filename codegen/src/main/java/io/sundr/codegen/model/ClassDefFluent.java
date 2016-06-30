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
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefFluent;

import java.util.List;
import java.util.Set;

public interface ClassDefFluent<A extends ClassDefFluent<A>> extends AbstractTypeDefFluent<io.sundr.codegen.model.ClassDef,ClassDefBuilder,A>{


    public A addToParameters(TypeParamDef... items);
    public A removeFromParameters(TypeParamDef... items);
    public List<TypeParamDef> getParameters();
    public A withParameters(List<TypeParamDef> parameters);
    public A withParameters(TypeParamDef... parameters);
    public ClassDefFluent.ParametersNested<A> addNewParameter();
    public ClassDefFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item);
    public A addToProperties(Property... items);
    public A removeFromProperties(Property... items);
    public Set<Property> getProperties();
    public A withProperties(Set<Property> properties);
    public A withProperties(Property... properties);
    public ClassDefFluent.PropertiesNested<A> addNewProperty();
    public ClassDefFluent.PropertiesNested<A> addNewPropertyLike(Property item);
    public A addToConstructors(Method... items);
    public A removeFromConstructors(Method... items);
    public Set<Method> getConstructors();
    public A withConstructors(Set<Method> constructors);
    public A withConstructors(Method... constructors);
    public ClassDefFluent.ConstructorsNested<A> addNewConstructor();
    public ClassDefFluent.ConstructorsNested<A> addNewConstructorLike(Method item);
    public A addToMethods(Method... items);
    public A removeFromMethods(Method... items);
    public Set<Method> getMethods();
    public A withMethods(Set<Method> methods);
    public A withMethods(Method... methods);
    public ClassDefFluent.MethodsNested<A> addNewMethod();
    public ClassDefFluent.MethodsNested<A> addNewMethodLike(Method item);

    public interface ParametersNested<N> extends Nested<N>,TypeParamDefFluent<ParametersNested<N>> {


    public N endParameter();    public N and();
}
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
