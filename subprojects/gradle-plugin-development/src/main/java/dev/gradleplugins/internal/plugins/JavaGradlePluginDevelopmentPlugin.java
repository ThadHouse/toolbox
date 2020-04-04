/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.gradleplugins.internal.plugins;

import dev.gradleplugins.GroovyGradlePluginDevelopmentExtension;
import dev.gradleplugins.JavaGradlePluginDevelopmentExtension;
import dev.gradleplugins.internal.DeferredRepositoryFactory;
import dev.gradleplugins.internal.GradlePluginDevelopmentExtensionInternal;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.plugin.devel.GradlePluginDevelopmentExtension;
import org.gradle.util.VersionNumber;

import static dev.gradleplugins.internal.plugins.AbstractGradlePluginDevelopmentPlugin.*;

public class JavaGradlePluginDevelopmentPlugin implements Plugin<Project> { //extends AbstractGradlePluginDevelopmentPlugin {
    private static final String PLUGIN_ID = "dev.gradleplugins.java-gradle-plugin";
    @Override
    public void apply(Project project) {
        assertOtherGradlePluginDevelopmentPluginsAreNeverApplied(project.getPluginManager(), PLUGIN_ID);
        assertJavaGradlePluginIsNotPreviouslyApplied(project.getPluginManager(), PLUGIN_ID);
        assertKotlinDslPluginIsNeverApplied(project.getPluginManager(), PLUGIN_ID);

        DeferredRepositoryFactory repositoryFactory = project.getObjects().newInstance(DeferredRepositoryFactory.class, project);

        project.getPluginManager().apply("java-gradle-plugin"); // For plugin development
        removeGradleApiProjectDependency(project);

        GradlePluginDevelopmentExtensionInternal extension = registerExtraExtension(project, JavaGradlePluginDevelopmentExtension.class);
        configureExtension(extension, project, repositoryFactory);

        project.getPluginManager().apply(GradlePluginDevelopmentFunctionalTestingPlugin.class);
    }
}