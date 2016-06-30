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
import io.sundr.codegen.model.EnumConstantRef;

public class EnumConstantRefBuilder extends EnumConstantRefFluentImpl<EnumConstantRefBuilder> implements VisitableBuilder<io.sundr.codegen.model.EnumConstantRef,EnumConstantRefBuilder>{

    EnumConstantRefFluent<?> fluent;
    Boolean validationEnabled;

    public EnumConstantRefBuilder(){
            this(true);
    }
    public EnumConstantRefBuilder(Boolean validationEnabled){
            this.fluent = this; this.validationEnabled=validationEnabled;
    }
    public EnumConstantRefBuilder(EnumConstantRefFluent<?> fluent){
            this(fluent, true);
    }
    public EnumConstantRefBuilder(EnumConstantRefFluent<?> fluent,Boolean validationEnabled){
            this.fluent = fluent; this.validationEnabled=validationEnabled;
    }
    public EnumConstantRefBuilder(EnumConstantRefFluent<?> fluent,EnumConstantRef instance){
            this(fluent, instance, true);
    }
    public EnumConstantRefBuilder(EnumConstantRefFluent<?> fluent, EnumConstantRef instance, Boolean validationEnabled){
            this.fluent = fluent; 
            fluent.withName(instance.getName()); 
            fluent.withArguments(instance.getArguments()); 
            fluent.withBody(instance.getBody()); 
            fluent.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }
    public EnumConstantRefBuilder(EnumConstantRef instance){
            this(instance,true);
    }
    public EnumConstantRefBuilder(EnumConstantRef instance, Boolean validationEnabled){
            this.fluent = this; 
            this.withName(instance.getName()); 
            this.withArguments(instance.getArguments()); 
            this.withBody(instance.getBody()); 
            this.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }

    public EditableEnumConstantRef build(){
            EditableEnumConstantRef buildable = new EditableEnumConstantRef(fluent.getName(),fluent.getArguments(),fluent.getBody(),fluent.getAttributes());
            validate(buildable);
            return buildable;
    }

    private <T>void validate(T item){
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            EnumConstantRefBuilder that = (EnumConstantRefBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
