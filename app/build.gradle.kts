plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    // Add the KSP plugin
    id("com.google.devtools.ksp")
    // 2. Apply the Hilt plugin
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.glucode_getitdone_to_do_list"
    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.glucode_getitdone_to_do_list"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.11.0")
    //implementation("androidx.lifecycle:lifecycle-livedata:2.11.0-ktx:2.11.0")

    //Extra icons
    implementation("androidx.compose.material:material-icons-extended")

    // Networking: Retrofit HTTP client
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // JSON Parsing: Converts API responses into Kotlin data classes
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation(libs.ads.mobile.sdk)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.media3.test.utils)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.transport.runtime)

    // Concurrency: Kotlin Coroutines for asynchronous API requests
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.11.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    // 1. Core Dagger Hilt Library
    implementation("com.google.dagger:hilt-android:2.60.1")

    // 2. Hilt Compiler (This is what actually generates the code for @Inject)
    ksp("com.google.dagger:hilt-android-compiler:2.60.1")
    // 3. Hilt Compose Navigation (Crucial for injecting ViewModels in your NavGraph)
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    val room_version = "2.8.4" // Check for the latest stable version

    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version") // Optional for Kotlin/Coroutines
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}