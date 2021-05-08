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

package io.sundr.adapter.source;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import io.sundr.adapter.api.Adapter;
import io.sundr.adapter.api.AdapterFactory;

public class SourceAdapterFactory
    implements AdapterFactory<SourceContext, TypeDeclaration, ClassOrInterfaceType, FieldDeclaration, MethodDeclaration> {

  @Override
  public Class getContextType() {
    return SourceContext.class;
  }

  @Override
  public Adapter<TypeDeclaration, ClassOrInterfaceType, FieldDeclaration, MethodDeclaration> create(SourceContext ctx) {
    return new SourceAdapter(ctx);
  }
}
