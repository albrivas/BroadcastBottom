import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.sonarqube.gradle.SonarQubeExtension

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

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("jacocoTestReport", JacocoReport::class) {
    reports {
        xml.required.set(true)
        html.required.set(false)
    }
}

// region Sonarqube Configuration

val sonarQubeExtension = project.extensions.getByName("sonarqube") as SonarQubeExtension
val sonarqubeFile = "sonar.properties"
val sonarProperties = gradleFileProperties(rootDir, sonarqubeFile)

sonarQubeExtension.properties {
    property("sonar.projectName", sonarProperties.getProperty("projectName"))
    property("sonar.projectKey", sonarProperties.getProperty("projectKey"))
    property("sonar.host.url", sonarProperties.getProperty("hostUrl"))
    property("sonar.language", sonarProperties.getProperty("language"))
    property("sonar.sources", sonarProperties.getProperty("sources"))
    property("sonar.login", sonarProperties.getProperty("loginToken"))
    property("sonar.test", sonarProperties.getProperty("testSource"))
    property("sonar.java.coveragePlugin", sonarProperties.getProperty("coveragePlugin"))
    property("sonar.android.lint.report", sonarProperties.getProperty("lintReport"))
}

//endregion