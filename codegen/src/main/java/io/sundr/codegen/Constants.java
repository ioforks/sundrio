/*
 *      Copyright 2018 The original authors.
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

package io.sundr.codegen;

import static io.sundr.codegen.functions.ClassTo.TYPEDEF;
import static io.sundr.codegen.functions.ClassTo.TYPEREF;
import static io.sundr.codegen.utils.TypeUtils.newTypeParamDef;
import static io.sundr.codegen.utils.TypeUtils.newTypeParamRef;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import io.sundr.model.PrimitiveRef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.model.VoidRef;
import io.sundr.model.WildcardRef;

public class Constants {

  public static final String EMPTY = "";
  public static final TypeParamDef F = newTypeParamDef("F");
  public static final TypeParamDef I = newTypeParamDef("I");
  public static final TypeParamDef O = newTypeParamDef("O");
  public static final TypeParamDef B = newTypeParamDef("B");
  public static final TypeParamDef T = newTypeParamDef("T");
  public static final TypeParamRef T_REF = newTypeParamRef("T");
  public static final TypeParamDef N = newTypeParamDef("N");
  public static final TypeParamRef N_REF = newTypeParamRef("N");
  public static final TypeParamDef V = newTypeParamDef("V");
  public static final VoidRef VOID = new VoidRef();
  public static final WildcardRef Q = new WildcardRef();

  public static final TypeDef BOOLEAN = TYPEDEF.apply(Boolean.class);
  public static final TypeRef BOOLEAN_REF = BOOLEAN.toInternalReference();

  public static final PrimitiveRef PRIMITIVE_BOOLEAN_REF = (PrimitiveRef) TYPEREF.apply(boolean.class);
  public static final PrimitiveRef PRIMITIVE_INT_REF = (PrimitiveRef) TYPEREF.apply(int.class);

  public static final TypeDef CLASS = TYPEDEF.apply(Class.class);
  public static final TypeDef OBJECT = TypeDef.OBJECT;
  public static final TypeRef OBJECT_REF = TypeDef.OBJECT.toReference();

  public static final TypeDef STRING = TYPEDEF.apply(String.class);
  public static final TypeRef STRING_REF = STRING.toReference();

  public static final TypeDef ARRAY = TYPEDEF.apply(Array.class);
  public static final TypeDef TYPE = TYPEDEF.apply(Type.class);
  public static final TypeDef TYPE_VARIABLE = TYPEDEF.apply(TypeVariable.class);
  public static final TypeDef GENERIC_ARRAY_TYPE = TYPEDEF.apply(GenericArrayType.class);
  public static final TypeDef PARAMETERIZED_TYPE = TYPEDEF.apply(ParameterizedType.class);
  public static final TypeRef INT_REF = TYPEREF.apply(int.class);
  public static final TypeDef BOXED_VOID = TYPEDEF.apply(Void.class);

  public static Class[] PRIMITIVES = { boolean.class, byte.class, char.class, short.class, int.class, long.class, double.class,
      float.class };

  public static TypeRef[] PRIMITIVE_TYPES = {
      TYPEREF.apply(boolean.class),
      TYPEREF.apply(byte.class),
      TYPEREF.apply(char.class),
      TYPEREF.apply(short.class),
      TYPEREF.apply(int.class),
      TYPEREF.apply(long.class),
      TYPEREF.apply(double.class),
      TYPEREF.apply(float.class)
  };

  public static TypeRef[] BOXED_PRIMITIVE_TYPES = {
      TYPEREF.apply(Boolean.class),
      TYPEREF.apply(Byte.class),
      TYPEREF.apply(Character.class),
      TYPEREF.apply(Short.class),
      TYPEREF.apply(Integer.class),
      TYPEREF.apply(Long.class),
      TYPEREF.apply(Double.class),
      TYPEREF.apply(Float.class)
  };

  public static String[] BOXED_PARSE_METHOD = {
      "parseBoolean",
      "parseByte",
      null,
      "parseShort",
      "parseInt",
      "parseLong",
      "parseDouble",
      "parseFloat"
  };
}
