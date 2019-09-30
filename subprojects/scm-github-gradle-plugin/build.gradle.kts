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