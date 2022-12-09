import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.guardsquare.appsweep") version "latest.release"
    id("com.android.application")
    kotlin("android")
    id("androidx.navigation.safeargs")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("jacoco")
    id("com.google.firebase.crashlytics")
}

val guardSquare = "appsweep.properties"
val guardSquareProperties = gradleFileProperties(rootDir, guardSquare)

android {
    namespace = "com.albrivas.broadcastbottom"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.albrivas.broadcastbottom"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    appsweep {
        apiKey = guardSquareProperties.getProperty("APPSWEEP_API_KEY")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "WEB_CLIENT_ID",
                gradleLocalProperties(rootDir).getProperty("webClientId")
            )
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "WEB_CLIENT_ID",
                gradleLocalProperties(rootDir).getProperty("webClientId")
            )
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(fileTree("libs") { include(listOf("*.jar")) })

    implementation(project(":uikit"))

    implementation(Libs.AndroidX.core)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.constraint)
    implementation(Libs.AndroidX.legacy)
    implementation(Libs.AndroidX.lifeCycle)
    implementation(Libs.AndroidX.viewmodel)
    implementation(Libs.AndroidX.navigationFragment)
    implementation(Libs.AndroidX.navigationUi)
    implementation(Libs.AndroidX.material)
    implementation(Libs.AndroidX.dataStore)
    implementation(Libs.AndroidX.stdlib)
    implementation(Libs.AndroidX.coroutines)
    implementation(Libs.AndroidX.coroutinesGoogleServices)

    implementation(Libs.Koin.koin)
    implementation(Libs.Koin.koinVM)
    implementation(Libs.Koin.koinFragment)
    implementation(Libs.Koin.koinScope)

    implementation(Libs.GoogleService.services)

    implementation(Libs.Firebase.firebaseCore)
    implementation(Libs.Firebase.firebaseMessaging)
    implementation(Libs.Firebase.firebaseServicesAuth)
    implementation(Libs.Firebase.firebaseServicesAuth)
    implementation(Libs.Firebase.firebaseGoogleAuth)
    implementation(Libs.Firebase.firebasePlayServices)
    implementation(Libs.Firebase.firebaseStorage)
    implementation(Libs.Firebase.firebaseFirestore)
    implementation(Libs.Firebase.firebaseFirebaseAuth)
    implementation(Libs.Firebase.firebaseAnalytics)
    implementation(Libs.Firebase.firebaseAuthKtx)

    implementation(Libs.Glide.glide)
    implementation(Libs.Coil.coil)

    implementation(Libs.Arrow.core)

    testImplementation(Libs.Test.Espresso.espresso)
    testImplementation(Libs.Test.core)
    testImplementation(Libs.Test.JUnit.junit)
    androidTestImplementation(Libs.Test.JUnit.test_junit)
    testImplementation(Libs.Test.Mockk.mockk)
    testImplementation(Libs.Test.Turbine.turbine)

    // Jetpack Compose
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.material3)
    implementation(Libs.AndroidX.Compose.activity)
    implementation(Libs.AndroidX.Compose.preview)
    androidTestImplementation(Libs.Test.Compose.uiTest)
    debugImplementation(Libs.Test.Compose.uiTooling)
    debugImplementation(Libs.Test.Compose.manifest)
}
