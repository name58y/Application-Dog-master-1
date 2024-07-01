plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.applicationdog"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.applicationdog"
        minSdk = 23
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        mlModelBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.5.0-alpha01")

    // TensorFlow Lite dependencies
    implementation("org.tensorflow:tensorflow-lite:2.8.0")
    implementation("org.tensorflow:tensorflow-lite-support:0.3.0")

    // CameraX dependencies
    implementation("androidx.camera:camera-camera2:1.1.0")
    implementation("androidx.camera:camera-lifecycle:1.1.0")
    implementation(libs.tensorflow.lite.metadata)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
