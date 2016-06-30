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

import io.sundr.builder.Builder;
import io.sundr.builder.VisitableBuilder;
import io.sundr.codegen.model.AbstractTypeDef;

public abstract class AbstractTypeDefBuilder<T extends io.sundr.codegen.model.TypeDef<T,B>,B extends Builder<T>> extends AbstractTypeDefFluentImpl<T,B,AbstractTypeDefBuilder<T,B>> implements VisitableBuilder<AbstractTypeDef<T,B>,AbstractTypeDefBuilder<T,B>>{

    AbstractTypeDefFluent<T,B,?> fluent;
    Boolean validationEnabled;

    public AbstractTypeDefBuilder(){
            this(true);
    }
    public AbstractTypeDefBuilder(Boolean validationEnabled){
            this.fluent = this; this.validationEnabled=validationEnabled;
    }
    public AbstractTypeDefBuilder(AbstractTypeDefFluent<T,B,?> fluent){
            this(fluent, true);
    }
    public AbstractTypeDefBuilder(AbstractTypeDefFluent<T,B,?> fluent,Boolean validationEnabled){
            this.fluent = fluent; this.validationEnabled=validationEnabled;
    }
    public AbstractTypeDefBuilder(AbstractTypeDefFluent<T,B,?> fluent,AbstractTypeDef<T,B> instance){
            this(fluent, instance, true);
    }
    public AbstractTypeDefBuilder(AbstractTypeDefFluent<T,B,?> fluent, AbstractTypeDef<T,B> instance, Boolean validationEnabled){
            this.fluent = fluent; 
            fluent.withKind(instance.getKind()); 
            fluent.withPackageName(instance.getPackageName()); 
            fluent.withName(instance.getName()); 
            fluent.withAnnotations(instance.getAnnotations()); 
            fluent.withExtendsList(instance.getExtendsList()); 
            fluent.withImplementsList(instance.getImplementsList()); 
            fluent.withOuterType(instance.getOuterType()); 
            fluent.withInnerTypes(instance.getInnerTypes()); 
            fluent.withModifiers(instance.getModifiers()); 
            fluent.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }
    public AbstractTypeDefBuilder(AbstractTypeDef<T,B> instance){
            this(instance,true);
    }
    public AbstractTypeDefBuilder(AbstractTypeDef<T,B> instance, Boolean validationEnabled){
            this.fluent = this; 
            this.withKind(instance.getKind()); 
            this.withPackageName(instance.getPackageName()); 
            this.withName(instance.getName()); 
            this.withAnnotations(instance.getAnnotations()); 
            this.withExtendsList(instance.getExtendsList()); 
            this.withImplementsList(instance.getImplementsList()); 
            this.withOuterType(instance.getOuterType()); 
            this.withInnerTypes(instance.getInnerTypes()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }

    public abstract AbstractTypeDef<T,B> build();
    private <T>void validate(T item){
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            AbstractTypeDefBuilder that = (AbstractTypeDefBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
