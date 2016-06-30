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
import io.sundr.codegen.model.ClassDef;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefFluent;
import io.sundr.codegen.model.EnumConstantRef;
import io.sundr.codegen.model.PrimitiveRef;
import io.sundr.codegen.model.PrimitiveRefFluent;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefFluent;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.model.VoidRef;
import io.sundr.codegen.model.VoidRefFluent;
import io.sundr.codegen.model.WildcardRef;
import io.sundr.codegen.model.WildcardRefFluent;

import java.util.List;

public interface EnumConstantRefFluent<A extends EnumConstantRefFluent<A>> extends io.sundr.codegen.model.AbstractTypeRefFluent<A> {


    public String getName();
    public A withName(String name);
    public A addToArguments(TypeRef... items);
    public A removeFromArguments(TypeRef... items);
    public List<TypeRef> getArguments();
    public A withArguments(List<TypeRef> arguments);
    public A withArguments(TypeRef... arguments);
    public A addToVoidRefArguments(VoidRef... items);
    public A removeFromVoidRefArguments(VoidRef... items);
    public EnumConstantRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgument();
    public EnumConstantRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(VoidRef item);
    public A addToEnumConstantRefArguments(EnumConstantRef... items);
    public A removeFromEnumConstantRefArguments(EnumConstantRef... items);
    public EnumConstantRefFluent.EnumConstantRefArgumentsNested<A> addNewEnumConstantRefArgument();
    public EnumConstantRefFluent.EnumConstantRefArgumentsNested<A> addNewEnumConstantRefArgumentLike(EnumConstantRef item);
    public A addToWildcardRefArguments(WildcardRef... items);
    public A removeFromWildcardRefArguments(WildcardRef... items);
    public EnumConstantRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgument();
    public EnumConstantRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(WildcardRef item);
    public A addToPrimitiveRefArguments(PrimitiveRef... items);
    public A removeFromPrimitiveRefArguments(PrimitiveRef... items);
    public EnumConstantRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument();
    public EnumConstantRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(PrimitiveRef item);
    public A addToTypeParamRefArguments(TypeParamRef... items);
    public A removeFromTypeParamRefArguments(TypeParamRef... items);
    public EnumConstantRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument();
    public EnumConstantRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item);
    public A addToClassRefArguments(ClassRef... items);
    public A removeFromClassRefArguments(ClassRef... items);
    public EnumConstantRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgument();
    public EnumConstantRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item);
    public ClassDef getBody();
    public A withBody(ClassDef body);
    public EnumConstantRefFluent.BodyNested<A> withNewBody();
    public EnumConstantRefFluent.BodyNested<A> withNewBodyLike(ClassDef item);
    public EnumConstantRefFluent.BodyNested<A> editBody();

    public interface VoidRefArgumentsNested<N> extends Nested<N>,VoidRefFluent<VoidRefArgumentsNested<N>> {


    public N endVoidRefArgument();    public N and();
}
    public interface EnumConstantRefArgumentsNested<N> extends Nested<N>,EnumConstantRefFluent<EnumConstantRefFluent.EnumConstantRefArgumentsNested<N>>{


    public N endEnumConstantRefArgument();    public N and();
}
    public interface WildcardRefArgumentsNested<N> extends Nested<N>,WildcardRefFluent<WildcardRefArgumentsNested<N>> {


    public N endWildcardRefArgument();    public N and();
}
    public interface PrimitiveRefArgumentsNested<N> extends Nested<N>,PrimitiveRefFluent<PrimitiveRefArgumentsNested<N>> {


    public N endPrimitiveRefArgument();    public N and();
}
    public interface TypeParamRefArgumentsNested<N> extends Nested<N>,TypeParamRefFluent<TypeParamRefArgumentsNested<N>> {


    public N endTypeParamRefArgument();    public N and();
}
    public interface ClassRefArgumentsNested<N> extends Nested<N>,ClassRefFluent<ClassRefArgumentsNested<N>> {


    public N endClassRefArgument();    public N and();
}
    public interface BodyNested<N> extends Nested<N>,ClassDefFluent<EnumConstantRefFluent.BodyNested<N>>{

        
    public N endBody();    public N and();
}


}
