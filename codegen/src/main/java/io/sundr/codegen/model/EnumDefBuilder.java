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
import io.sundr.codegen.model.EnumDef;

public class EnumDefBuilder extends EnumDefFluentImpl<EnumDefBuilder> implements VisitableBuilder<io.sundr.codegen.model.EnumDef,EnumDefBuilder>{

    EnumDefFluent<?> fluent;
    Boolean validationEnabled;

    public EnumDefBuilder(){
            this(true);
    }
    public EnumDefBuilder(Boolean validationEnabled){
            this.fluent = this; this.validationEnabled=validationEnabled;
    }
    public EnumDefBuilder(EnumDefFluent<?> fluent){
            this(fluent, true);
    }
    public EnumDefBuilder(EnumDefFluent<?> fluent,Boolean validationEnabled){
            this.fluent = fluent; this.validationEnabled=validationEnabled;
    }
    public EnumDefBuilder(EnumDefFluent<?> fluent,EnumDef instance){
            this(fluent, instance, true);
    }
    public EnumDefBuilder(EnumDefFluent<?> fluent, EnumDef instance, Boolean validationEnabled){
            this.fluent = fluent; 
            fluent.withPackageName(instance.getPackageName()); 
            fluent.withName(instance.getName()); 
            fluent.withAnnotations(instance.getAnnotations()); 
            fluent.withImplementsList(instance.getImplementsList()); 
            fluent.withInnerTypes(instance.getInnerTypes()); 
            fluent.withOuterType(instance.getOuterType()); 
            fluent.withProperties(instance.getProperties()); 
            fluent.withConstructors(instance.getConstructors()); 
            fluent.withMethods(instance.getMethods()); 
            fluent.withModifiers(instance.getModifiers()); 
            fluent.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }
    public EnumDefBuilder(EnumDef instance){
            this(instance,true);
    }
    public EnumDefBuilder(EnumDef instance, Boolean validationEnabled){
            this.fluent = this; 
            this.withPackageName(instance.getPackageName()); 
            this.withName(instance.getName()); 
            this.withAnnotations(instance.getAnnotations()); 
            this.withImplementsList(instance.getImplementsList()); 
            this.withInnerTypes(instance.getInnerTypes()); 
            this.withOuterType(instance.getOuterType()); 
            this.withProperties(instance.getProperties()); 
            this.withConstructors(instance.getConstructors()); 
            this.withMethods(instance.getMethods()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }

    public EnumDef build(){
            EnumDef buildable = new EnumDef(fluent.getPackageName(),fluent.getName(),fluent.getAnnotations(),fluent.getImplementsList(),fluent.getInnerTypes(),fluent.getOuterType(),fluent.getProperties(),fluent.getConstructors(),fluent.getMethods(),fluent.getModifiers(),fluent.getAttributes());
            validate(buildable);
            return buildable;
    }

    private <T>void validate(T item){
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            EnumDefBuilder that = (EnumDefBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
