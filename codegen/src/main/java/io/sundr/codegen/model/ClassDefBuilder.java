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

public class ClassDefBuilder extends ClassDefFluentImpl<ClassDefBuilder> implements VisitableBuilder<ClassDef,ClassDefBuilder> {

    ClassDefFluent<?> fluent;
    Boolean validationEnabled;

    public ClassDefBuilder(){
            this(true);
    }
    public ClassDefBuilder(Boolean validationEnabled){
            this.fluent = this; this.validationEnabled=validationEnabled;
    }
    public ClassDefBuilder(ClassDefFluent<?> fluent){
            this(fluent, true);
    }
    public ClassDefBuilder(ClassDefFluent<?> fluent,Boolean validationEnabled){
            this.fluent = fluent; this.validationEnabled=validationEnabled;
    }
    public ClassDefBuilder(ClassDefFluent<?> fluent,ClassDef instance){
            this(fluent, instance, true);
    }
    public ClassDefBuilder(ClassDefFluent<?> fluent, ClassDef instance, Boolean validationEnabled){
            this.fluent = fluent; 
            fluent.withKind(instance.getKind()); 
            fluent.withPackageName(instance.getPackageName()); 
            fluent.withName(instance.getName()); 
            fluent.withAnnotations(instance.getAnnotations()); 
            fluent.withExtendsList(instance.getExtendsList()); 
            fluent.withImplementsList(instance.getImplementsList()); 
            fluent.withParameters(instance.getParameters()); 
            fluent.withProperties(instance.getProperties()); 
            fluent.withConstructors(instance.getConstructors()); 
            fluent.withMethods(instance.getMethods()); 
            fluent.withOuterType(instance.getOuterType()); 
            fluent.withInnerTypes(instance.getInnerTypes()); 
            fluent.withModifiers(instance.getModifiers()); 
            fluent.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }
    public ClassDefBuilder(ClassDef instance){
            this(instance,true);
    }
    public ClassDefBuilder(ClassDef instance, Boolean validationEnabled){
            this.fluent = this; 
            this.withKind(instance.getKind()); 
            this.withPackageName(instance.getPackageName()); 
            this.withName(instance.getName()); 
            this.withAnnotations(instance.getAnnotations()); 
            this.withExtendsList(instance.getExtendsList()); 
            this.withImplementsList(instance.getImplementsList()); 
            this.withParameters(instance.getParameters()); 
            this.withProperties(instance.getProperties()); 
            this.withConstructors(instance.getConstructors()); 
            this.withMethods(instance.getMethods()); 
            this.withOuterType(instance.getOuterType()); 
            this.withInnerTypes(instance.getInnerTypes()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }

    public EditableClassDef build(){
            EditableClassDef buildable = new EditableClassDef(fluent.getKind(),fluent.getPackageName(),fluent.getName(),fluent.getAnnotations(),fluent.getExtendsList(),fluent.getImplementsList(),fluent.getParameters(),fluent.getProperties(),fluent.getConstructors(),fluent.getMethods(),fluent.getOuterType(),fluent.getInnerTypes(),fluent.getModifiers(),fluent.getAttributes());
            validate(buildable);
            return buildable;
    }

    private <T>void validate(T item){
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            ClassDefBuilder that = (ClassDefBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
