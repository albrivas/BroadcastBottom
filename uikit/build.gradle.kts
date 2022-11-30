plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.albrivas.uikit"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
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

    implementation(Libs.AndroidX.core)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.material)

    //Test
    androidTestImplementation(Libs.Test.Espresso.espresso)
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