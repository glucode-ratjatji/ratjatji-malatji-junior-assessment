plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    // Add the KSP plugin
    id("com.google.devtools.ksp")
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
    //Extra icons
    implementation("androidx.compose.material:material-icons-extended")

    // Networking: Retrofit HTTP client
    implementation("com.squareup.retrofit2:retrofit:3.0.0")

    // JSON Parsing: Converts API responses into Kotlin data classes
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation(libs.androidx.navigation.compose)

    // Concurrency: Kotlin Coroutines for asynchronous API requests
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.11.0")

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