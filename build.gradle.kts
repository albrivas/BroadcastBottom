apply(plugin = "org.sonarqube")
apply(plugin = "jacoco")
apply(plugin = "com.github.ben-manes.versions")

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Libs.buildGradle)
        classpath(Libs.kotlinPlugin)
        classpath(Libs.safeargs)
        classpath(Libs.googleServices)
        classpath(Libs.crashlyticsGradle)
        classpath(Libs.sonarGradle)
        classpath(Libs.jacocoGradle)
        classpath(Libs.gradleVersionsPlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://plugins.gradle.org/m2/")
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}