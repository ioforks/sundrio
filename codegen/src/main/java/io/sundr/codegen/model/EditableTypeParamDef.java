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

import io.sundr.builder.Editable;
import io.sundr.codegen.model.*;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;

import java.util.List;
import java.util.Map;

public class EditableTypeParamDef extends io.sundr.codegen.model.TypeParamDef implements Editable<io.sundr.codegen.model.TypeParamDefBuilder>{


public EditableTypeParamDef(String name , List<io.sundr.codegen.model.ClassRef> bounds , Map<String, Object> attributes ){
    super(name, bounds, attributes);
}

public io.sundr.codegen.model.TypeParamDefBuilder edit(){
    return new io.sundr.codegen.model.TypeParamDefBuilder(this);
}


}
    
