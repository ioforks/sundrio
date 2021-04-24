/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.codegen.functions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.TypeDef;

public class CollectionsTest {

  public static final TypeDef LIST = ClassTo.TYPEDEF.apply(List.class);

  @Test
  public void testCollections() throws Exception {

    ClassRef list = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.List")
        .build();

    ClassRef listOfString = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.List")
        .addNewClassRefArgument()
        .withFullyQualifiedName("java.lang.String")
        .endClassRefArgument()
        .build();

    ClassRef arrayList = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.ArrayList")
        .build();

    ClassRef arrayListOfString = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.ArrayList")
        .addNewClassRefArgument()
        .withFullyQualifiedName("java.lang.String")
        .endClassRefArgument()
        .build();

    ClassRef map = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.Map")
        .build();

    assertTrue(Collections.IS_LIST.apply(list));
    assertTrue(Collections.IS_LIST.apply(listOfString));
    assertTrue(Collections.IS_LIST.apply(arrayList));
    assertTrue(Collections.IS_LIST.apply(arrayListOfString));
    assertFalse(Collections.IS_LIST.apply(map));
  }
}
