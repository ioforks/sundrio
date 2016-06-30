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
import io.sundr.codegen.model.EditableWildcardRef;
import io.sundr.codegen.model.WildcardRef;
import io.sundr.codegen.model.WildcardRefFluent;
import io.sundr.codegen.model.WildcardRefFluentImpl;

public class WildcardRefBuilder extends io.sundr.codegen.model.WildcardRefFluentImpl<WildcardRefBuilder> implements VisitableBuilder<io.sundr.codegen.model.WildcardRef,WildcardRefBuilder>{

    io.sundr.codegen.model.WildcardRefFluent<?> fluent;

    public WildcardRefBuilder(){
            this(new io.sundr.codegen.model.WildcardRef());
        }
    public WildcardRefBuilder(io.sundr.codegen.model.WildcardRefFluent<?> fluent){
            this(fluent, new io.sundr.codegen.model.WildcardRef());
        }
    public WildcardRefBuilder(io.sundr.codegen.model.WildcardRefFluent<?> fluent, io.sundr.codegen.model.WildcardRef instance){
            this.fluent = fluent;
            fluent.withBounds(instance.getBounds());
            fluent.withAttributes(instance.getAttributes());
        }
    public WildcardRefBuilder(io.sundr.codegen.model.WildcardRef instance){
            this.fluent = this;
            this.withBounds(instance.getBounds());
            this.withAttributes(instance.getAttributes());
        }

    public io.sundr.codegen.model.EditableWildcardRef build(){
            io.sundr.codegen.model.EditableWildcardRef buildable = new io.sundr.codegen.model.EditableWildcardRef(fluent.getBounds(),fluent.getAttributes());
            validate(buildable);
            return buildable;
        }
    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            WildcardRefBuilder that = (WildcardRefBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            return true;
        }

private <T> void validate(T item) {}


}
    
