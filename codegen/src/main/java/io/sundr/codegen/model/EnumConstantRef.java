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

import io.sundr.codegen.model.*;
import io.sundr.codegen.model.AbstractTypeRef;
import io.sundr.codegen.model.ClassDef;
import io.sundr.codegen.model.TypeRef;

import java.util.List;
import java.util.Map;

public class EnumConstantRef extends io.sundr.codegen.model.AbstractTypeRef {

    private final String name;
    private final List<io.sundr.codegen.model.TypeRef> arguments;
    private final io.sundr.codegen.model.ClassDef body;

    public EnumConstantRef(String name, List<io.sundr.codegen.model.TypeRef> arguments, io.sundr.codegen.model.ClassDef body, Map<String, Object> attributes) {
        super(attributes);
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public List<io.sundr.codegen.model.TypeRef> getArguments() {
        return arguments;
    }

    public io.sundr.codegen.model.ClassDef getBody() {
        return body;
    }

    @Override
    public boolean isAssignableFrom(io.sundr.codegen.model.TypeRef ref) {
        return false;
    }

    @Override
    public int getDimensions() {
        return 0;
    }

    @Override
    public io.sundr.codegen.model.TypeRef withDimensions(int dimensions) {
        return this;
    }
}