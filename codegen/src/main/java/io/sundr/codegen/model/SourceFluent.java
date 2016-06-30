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

import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;

import java.util.List;

public interface SourceFluent<A extends SourceFluent<A>> extends Fluent<A>{


    public A addToTypes(TypeDef... items);
    public A removeFromTypes(TypeDef... items);
    public List<TypeDef> getTypes();
    public A withTypes(List<TypeDef> types);
    public A withTypes(TypeDef... types);
    public A addToClassDefTypes(ClassDef... items);
    public A removeFromClassDefTypes(ClassDef... items);
    public SourceFluent.ClassDefTypesNested<A> addNewClassDefType();
    public SourceFluent.ClassDefTypesNested<A> addNewClassDefTypeLike(ClassDef item);
    public A addToEnumDefTypes(EnumDef... items);
    public A removeFromEnumDefTypes(EnumDef... items);
    public SourceFluent.EnumDefTypesNested<A> addNewEnumDefType();
    public SourceFluent.EnumDefTypesNested<A> addNewEnumDefTypeLike(EnumDef item);

    public interface ClassDefTypesNested<N> extends Nested<N>,ClassDefFluent<SourceFluent.ClassDefTypesNested<N>>{


    public N endClassDefType();    public N and();
}
    public interface EnumDefTypesNested<N> extends Nested<N>,EnumDefFluent<SourceFluent.EnumDefTypesNested<N>>{

        
    public N endEnumDefType();    public N and();
}


}
