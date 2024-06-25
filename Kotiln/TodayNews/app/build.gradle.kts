plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.inik.todaynews"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.inik.todaynews"
        minSdk = 28
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.tickaroo.tikxml:retrofit-converter:0.8.13")
    implementation ("com.tickaroo.tikxml:core:0.8.13")
    implementation ("com.tickaroo.tikxml:annotation:0.8.13")
    kapt ("com.tickaroo.tikxml:processor:0.8.13")

//    annotationProcessor ("com.github.Tickaroo.tikxml:processor-common:0.8.13")
//    implementation ("com.tickaroo.tikxml:converter-date-rfc3339:0.8.13") {
//        exclude group: "com.tickaroo.tikxml", module: "core"
//    }

//    implementation 'com.github.Tickaroo.tikxml:annotation:0.8.15'
//    implementation 'com.github.Tickaroo.tikxml:core:0.8.15'r
//    kapt 'com.github.Tickaroo.tikxml:processor-common:0.8.15'
//    kapt 'com.github.Tickaroo.tikxml:processor:0.8.15'
}