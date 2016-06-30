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

public class SourceBuilder extends SourceFluentImpl<SourceBuilder> implements VisitableBuilder<Source,SourceBuilder>{

    SourceFluent<?> fluent;
    Boolean validationEnabled;

    public SourceBuilder(){
            this(true);
    }
    public SourceBuilder(Boolean validationEnabled){
            this(new Source(), validationEnabled);
    }
    public SourceBuilder(SourceFluent<?> fluent){
            this(fluent, true);
    }
    public SourceBuilder(SourceFluent<?> fluent,Boolean validationEnabled){
            this(fluent, new Source(), validationEnabled);
    }
    public SourceBuilder(SourceFluent<?> fluent,Source instance){
            this(fluent, instance, true);
    }
    public SourceBuilder(SourceFluent<?> fluent,Source instance,Boolean validationEnabled){
            this.fluent = fluent; 
            fluent.withTypes(instance.getTypes()); 
            this.validationEnabled = validationEnabled; 
    }
    public SourceBuilder(Source instance){
            this(instance,true);
    }
    public SourceBuilder(Source instance,Boolean validationEnabled){
            this.fluent = this; 
            this.withTypes(instance.getTypes()); 
            this.validationEnabled = validationEnabled; 
    }

    public EditableSource build(){
            EditableSource buildable = new EditableSource(fluent.getTypes());
            validate(buildable);
            return buildable;
    }

    private <T>void validate(T item){
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            SourceBuilder that = (SourceBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
