plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    alias(libs.plugins.navigation.safe.args)
    kotlin("kapt")
}

android {
    namespace = "com.example.dexdogs"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.dexdogs"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.44") // Hilt core
    kapt("com.google.dagger:hilt-android-compiler:2.44") // Hilt compiler
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.kotlin)
    implementation("io.coil-kt:coil:2.6.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.2")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.2")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // CameraX Core
    implementation ("androidx.camera:camera-core:1.3.1")
    implementation ("androidx.camera:camera-camera2:1.3.1")

    // Lifecycle para vincular la cámara al ciclo de vida del activity/fragment
    implementation ("androidx.camera:camera-lifecycle:1.3.1")

    // CameraX View (Para mostrar la cámara en un PreviewView)
    implementation ("androidx.camera:camera-view:1.3.1")

    // CameraX Extensions (Opcional: filtros como HDR, Night Mode, etc.)
    implementation ("androidx.camera:camera-extensions:1.3.1")

    implementation("com.github.chuckerteam.chucker:library:3.5.2") // Para release, no muestra logs
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")



}