/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.builder.internal.processor;

import io.sundr.builder.Constants;
import io.sundr.builder.annotations.ExternalBuildables;
import io.sundr.builder.annotations.Inline;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.functions.ElementTo;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.utils.ModelUtils;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.EDIATABLE_ENABLED;
import static io.sundr.builder.Constants.VALIDATION_ENABLED;

@SupportedAnnotationTypes("io.sundr.builder.annotations.ExternalBuildables")
public class ExternalBuildableProcessor extends AbstractBuilderProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();
        Types types = processingEnv.getTypeUtils();
        Filer filer = processingEnv.getFiler();


        ExternalBuildables generated = null;
        BuilderContext ctx = null;

        //First pass register all externals
        for (TypeElement annotation : annotations) {
            for (Element element : env.getElementsAnnotatedWith(annotation)) {
                generated = element.getAnnotation(ExternalBuildables.class);
                if (generated == null) {
                    continue;
                }
                ctx = BuilderContextManager.create(elements, types, generated.validationEnabled(), generated.generateBuilderPackage(), generated.builderPackage());

                for (String name : generated.value()) {
                    PackageElement packageElement = elements.getPackageElement(name);
                    List<TypeElement> typeElements = new ArrayList<>();

                    if (packageElement != null) {
                        for (Element e : packageElement.getEnclosedElements()) {
                            if (e instanceof TypeElement) {
                                typeElements.add((TypeElement) e);
                            }
                        }
                    } else {
                        TypeElement e = elements.getTypeElement(name);
                        if (e != null) {
                            typeElements.add(e);
                        }
                    }


                    for (TypeElement typeElement : typeElements) {
                        TypeDef b = new TypeDefBuilder(ElementTo.TYPEDEF.apply(ModelUtils.getClassElement(typeElement)))
                                .addToAttributes(EDIATABLE_ENABLED, generated.editableEnabled())
                                .addToAttributes(VALIDATION_ENABLED, generated.validationEnabled())
                                .build();

                        ctx.getDefinitionRepository().register(b);
                        ctx.getBuildableRepository().register(b);
                    }
                }

                for (TypeElement ref : BuilderUtils.getBuildableReferences(ctx, generated)) {
                    TypeDef r = new TypeDefBuilder(ElementTo.TYPEDEF.apply(ModelUtils.getClassElement(ref)))
                            .addToAttributes(EDIATABLE_ENABLED, generated.editableEnabled())
                            .addToAttributes(VALIDATION_ENABLED, generated.validationEnabled())
                            .build();

                    ctx.getDefinitionRepository().register(r);
                    ctx.getBuildableRepository().register(r);
                }
            }
        }

        if (ctx == null) {
            return true;
        }

        generateLocalDependenciesIfNeeded();
        addCustomMappings(ctx);
        ctx.getDefinitionRepository().updateReferenceMap();
        generatePojos(ctx);


        int total = ctx.getBuildableRepository().getBuildables().size();
        int count = 0;
        for (TypeDef typeDef : ctx.getBuildableRepository().getBuildables()) {
            try {
                double percentage = 100 * (count++) / total;
                System.err.println(Math.round(percentage)+"%: " + typeDef.getFullyQualifiedName());

                generateFromClazz(ClazzAs.FLUENT_INTERFACE.apply(typeDef),
                        Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);

                if (typeDef.isInterface()) {
                    continue;
                }

                generateFromClazz(ClazzAs.FLUENT_IMPL.apply(typeDef),
                        Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);

                if (generated.editableEnabled()) {
                    generateFromClazz(ClazzAs.EDITABLE_BUILDER.apply(typeDef),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);

                    generateFromClazz(ClazzAs.EDITABLE.apply(typeDef),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);
                } else {
                    generateFromClazz(ClazzAs.BUILDER.apply(typeDef),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);
                }


                for (final Inline inline : generated.inline()) {
                    generateFromClazz(inlineableOf(ctx, typeDef, inline),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.err.println("100%: Builder generation complete.");
        return true;
    }
}
