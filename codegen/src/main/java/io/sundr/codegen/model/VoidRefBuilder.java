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

import io.sundr.builder.VisitableBuilder;
import io.sundr.codegen.model.*;
import io.sundr.codegen.model.EditableVoidRef;
import io.sundr.codegen.model.VoidRef;
import io.sundr.codegen.model.VoidRefFluent;
import io.sundr.codegen.model.VoidRefFluentImpl;

public class VoidRefBuilder extends VoidRefFluentImpl<io.sundr.codegen.model.VoidRefBuilder> implements VisitableBuilder<VoidRef, io.sundr.codegen.model.VoidRefBuilder>{

     VoidRefFluent<?> fluent;

    public VoidRefBuilder(){
        this(new VoidRef());
    }
    public VoidRefBuilder( VoidRefFluent<?> fluent ){
        this(fluent, new VoidRef());
    }
    public VoidRefBuilder(VoidRefFluent<?> fluent , VoidRef instance ){
        this.fluent = fluent; fluent.withAttributes(instance.getAttributes());
    }
    public VoidRefBuilder( VoidRef instance ){
        this.fluent = this; this.withAttributes(instance.getAttributes());
    }

public EditableVoidRef build(){
    EditableVoidRef buildable = new EditableVoidRef(fluent.getAttributes());
validate(buildable);
return buildable;

}
public boolean equals( Object o ){
    
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
if (!super.equals(o)) return false;
io.sundr.codegen.model.VoidRefBuilder that = (io.sundr.codegen.model.VoidRefBuilder) o;
if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;
return true;

}

private <T> void validate(T item) {}


}
    
