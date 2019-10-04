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
        register("licensePlugin") {
            id = "dev.gradleplugins.license"
            implementationClass = "dev.gradleplugins.license.internal.OpenSourceSoftwareLicensePlugin"
            description = "License management for Gradle builds"
        }
    }
}

// TODO: remove when 0.0.12 is released
repositories {
    jcenter()
}

pluginBundle {
    website = "https://gradleplugins.dev/"
    tags = listOf("license")

    plugins {
        val licensePlugin by existing {
            displayName = "License management for Gradle builds"
        }
    }
}

// Brainstorm for license plugin
// dev.gradleplugins.license
// dev.gradleplugins.license.apache-2.0
// dev.gradleplugins.license.mit
/*

// custom
license.use("apache-2.0") // Not available when using a direct license plugin

// Those are read only properties
license.name == "Apache-2.0"
license.shortName == "ASL2"
license.displayName == "The Apache Software License, Version 2.0"
license.copyrightFileHeader == "..."


// Don't allow own license definition

Attach task to write the LICENSE file to setup task

Attach task to lint file headers to check
Add task to update file headers license (tricky)
Add task to report on the license of the dependencies
Add task to report incompatible dependency license

LICENSE file and what is declared on the model should be checked during check task if the model was forced to a value. It should suggest how to fix this (using the setup plugin).
 */