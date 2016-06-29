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

package io.sundr.examples.codegen;

import io.sundr.builder.Builder;

import java.util.Set;

/**
 * Created by iocanel on 6/29/16.
 */
public interface TypeDef<T extends TypeDef<T,B>, B extends Builder<T>> {

    B edit();
    Set<ClassRef> getReferences();

    Kind getKind();
    String getPackageName();
    String getName();
    Set<ClassRef> getAnnotations();
    Set<ClassRef> getExtendsList();
    Set<ClassRef> getImplementsList();
    TypeDef getOuterType();
    Set<TypeDef> getInnerTypes();

}
