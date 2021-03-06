///*
// * Copyright 2019 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package dev.gradleplugins.internal.ide;
//
//import org.gradle.api.Plugin;
//import org.gradle.api.Project;
//import org.gradle.plugins.ide.idea.IdeaPlugin;
//import org.gradle.plugins.ide.idea.model.IdeaModel;
//
//public class IdePlugin implements Plugin<Project> {
//    @Override
//    public void apply(Project project) {
//        project.getPluginManager().withPlugin("org.jetbrains.gradle.plugin.idea-ext", appliedPlugin -> {
//            project.getTasks().named("idea", task -> {
//                task.doFirst(it -> {
//                    throw new RuntimeException("To import in IntelliJ, please follow the instructions here: https://github.com/gradle/gradle/blob/master/CONTRIBUTING.md#intellij but use this repo ;-)");
//                });
//            });
//
//            project.getPlugins().withType(IdeaPlugin.class, plugin -> {
//                IdeaModel model = project.getExtensions().getByType((IdeaModel.class));
//                model.project(ideaProject -> {
//                    ideaProject.setJdkName("8.0");
//                    ideaProject.getWildcards().add("?*.gradle");
//                });
//            });
//        });
//    }
//}
