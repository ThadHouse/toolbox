import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    dev.gradleplugins.`shaded-artifact`

    // Supported by the development plugins
    `kotlin-dsl`
    `groovy`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version("0.10.1")
}

// Supported by the development plugins
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    shaded("com.gradle.publish:plugin-publish-plugin:0.10.1")
    implementation(gradleTestKit())
}

val generatorTask = tasks.register("createVersionInformation") {
    outputs.dir(project.layout.buildDirectory.dir("generatedSources"))
    inputs.property("version", project.version)
    inputs.property("group", project.group)
    inputs.property("name", project.project(":gradle-testkit-fixtures").name)
    doLast {
        project.layout.buildDirectory.file("generatedSources/TestFixtures.kt").get().asFile.writeText("""package dev.gradleplugins.internal

object TestFixtures {
    var released = ${!project.version.toString().contains("-SNAPSHOT")}
    var notation = "${project.group}:${project.project(":gradle-testkit-fixtures").name}:${project.version}"
}
""")
    }
}

tasks.named<KotlinCompile>("compileKotlin") {
    dependsOn(generatorTask)
}

sourceSets.main.configure {
    withConvention(KotlinSourceSet::class) {
        kotlin.srcDir(project.layout.buildDirectory.dir("generatedSources"))
    }
}

gradlePlugin {
    plugins {
        create("javaGradlePluginDevelopment") {
            id = "dev.gradleplugins.java-gradle-plugin"
            implementationClass = "dev.gradleplugins.internal.JavaGradlePluginDevelopmentPlugin"
        }
        create("groovyGradlePluginDevelopment") {
            id = "dev.gradleplugins.groovy-gradle-plugin"
            implementationClass = "dev.gradleplugins.internal.GroovyGradlePluginDevelopmentPlugin"
        }
        create("kotlinGradlePluginDevelopment") {
            id = "dev.gradleplugins.kotlin-gradle-plugin"
            implementationClass = "dev.gradleplugins.internal.KotlinGradlePluginDevelopmentPlugin"
        }
    }
}

pluginBundle {
    website = "https://gradleplugins.dev/"
    vcsUrl = "https://github.com/gradle-plugins/development-gradle-plugin"
    description = "Sets of highly opinionated plugins to kick start any."
    tags = listOf("gradle", "gradle-plugins", "development")

    plugins {
        val javaGradlePluginDevelopment by existing {
            // id is captured from java-gradle-plugin configuration
            displayName = "Visual Studio Code Gradle Plugin"
        }
        val groovyGradlePluginDevelopment by existing {
            // id is captured from java-gradle-plugin configuration
            displayName = "Visual Studio Code Gradle Plugin"
        }
        val kotlinGradlePluginDevelopment by existing {
            // id is captured from java-gradle-plugin configuration
            displayName = "Visual Studio Code Gradle Plugin"
        }
    }
}

shadedArtifact {
    packagesToRelocate.set(listOf("com.gradle.publish"))
}