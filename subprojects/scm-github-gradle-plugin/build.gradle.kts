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

plugins {
    id("dev.gradleplugins.java-gradle-plugin")
    dev.gradleplugins.experimental.ide
}

gradlePlugin {
    plugins {
        register("gitHubPlugin") {
            id = "dev.gradleplugins.scm.github"
            implementationClass = "dev.gradleplugins.scm.internal.GitHubSourceControlManagerPlugin"
            description = "GitHub source control manager modeling for Gradle builds"
        }
    }
}

pluginBundle {
    website = "https://gradleplugins.dev/"
    tags = listOf("scm", "github")

    plugins {
        val gitHubPlugin by existing {
            displayName = "GitHub source control manager modeling for Gradle builds"
        }
    }
}

// This plugin should install extension for helping with the common use cases:
// - resolving the GitHub URL of a file within the repo (check if a file is part of the repo)
// - open a PR on the GitHub repo
// - open issues
// - get/set repository tags
// - get/set repository description
// - get/set repository website

// This plugin should apply a base git plugin that give the following capability general to Git:
// - Get the current commit hash
// - Get versions
// - Get branches
// - Get commits

// This base git plugin should apply a base scm plugin that give even more general capabilities:
// - Is the current workspace contains any unchecked files (dirty)