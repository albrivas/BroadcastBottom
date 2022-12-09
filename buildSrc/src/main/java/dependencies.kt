object Libs {

    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.44.0"
    const val buildGradle = "com.android.tools.build:gradle:7.3.1"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10"
    const val safeargs = "androidx.navigation:navigation-safe-args-gradle-plugin:2.5.2"
    const val googleServices = "com.google.gms:google-services:4.3.14"
    const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:2.9.2"
    const val sonarGradle = "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.4.0.2513"
    const val jacocoGradle = "org.jacoco:org.jacoco.core:0.8.8"

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.8.0"
        const val appCompat = "androidx.appcompat:appcompat:1.5.1"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
        const val lifeCycle = "androidx.lifecycle:lifecycle-extensions:2.2.0"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:2.5.2"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:2.5.2"
        const val material = "com.google.android.material:material:1.7.0"
        const val dataStore = "androidx.datastore:datastore-preferences:1.0.0-alpha04"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.10"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7"
        const val coroutinesGoogleServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4"

        object Compose {
            const val ui = "androidx.compose.ui:ui:1.3.1"
            const val material = "androidx.compose.material:material:1.3.1"
            const val material3 = "androidx.compose.material3:material3:1.0.1"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
            const val activity = "androidx.activity:activity-compose:1.6.1"
            const val preview = "androidx.compose.ui:ui-tooling-preview:1.3.1"
        }

        object Navigation {
            private const val version = "2.5.2"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs =
                "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Room {
            private const val version = "2.4.2"
            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }
    }

    object Test {

        const val core = "androidx.test:core:1.5.0"

        object Espresso {
            private const val version = "3.5.0"
            const val espresso = "androidx.test.espresso:espresso-core:$version"
        }

        object JUnit {
            const val junit = "junit:junit:4.13.2"
            const val test_junit = "androidx.test.ext:junit:1.1.4"
        }

        object Compose {
            const val uiTest = "androidx.compose.ui:ui-test-junit4:1.3.1"
            const val uiTooling = "androidx.compose.ui:ui-tooling:1.3.1"
            const val manifest = "androidx.compose.ui:ui-test-manifest:1.3.1"
        }

        object Mockk {
            private const val version = "1.12.4"
            const val mockk = "io.mockk:mockk:$version"
        }

        object Turbine {
            private const val version = "0.12.1"
            const val turbine = "app.cash.turbine:turbine:$version"
        }
    }

    object Glide {
        private const val version = "4.14.2"
        const val glide = "com.github.bumptech.glide:glide:$version"
    }

    object Coil {
        const val coil = "io.coil-kt:coil-compose:2.2.1"
    }

    object Arrow {
        private const val version = "1.1.2"
        const val core = "io.arrow-kt:arrow-core:$version"
    }

    object Hilt {
        private const val version = "2.42"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object Gson {
        private const val version = "2.9.0"
        const val gson = "com.google.code.gson:gson:$version"
    }

    object JavaX {
        const val inject = "javax.inject:javax.inject:1"
    }

    object GoogleService {
        const val services = "com.google.gms:google-services:4.3.13"
        const val auth = "com.google.android.gms:play-services-auth:20.2.0"
    }

    object Firebase {
        const val firebaseCore = "com.google.firebase:firebase-core:18.0.0"
        const val firebaseMessaging = "com.google.firebase:firebase-messaging:21.0.0"
        const val firebaseServicesAuth = "com.google.android.gms:play-services-auth:20.3.0"
        const val firebaseGoogleAuth = "com.google.firebase:firebase-auth"
        const val firebasePlayServices = "com.google.android.gms:play-services-base:18.1.0"
        const val firebaseStorage = "com.google.firebase:firebase-storage:19.2.0"
        const val firebaseFirestore = "com.google.firebase:firebase-firestore:22.0.0"
        const val firebaseFirebaseAuth = "com.google.firebase:firebase-auth:20.0.0"
        const val firebaseAuthKtx = "com.google.firebase:firebase-auth-ktx:21.0.2"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics:18.0.0"
    }

    object Koin {
        private const val version = "2.1.5"
        const val koin = "org.koin:koin-android:$version"
        const val koinScope = "org.koin:koin-androidx-scope:$version"
        const val koinVM = "org.koin:koin-androidx-viewmodel:$version"
        const val koinFragment = "org.koin:koin-androidx-fragment:$version"
    }
}