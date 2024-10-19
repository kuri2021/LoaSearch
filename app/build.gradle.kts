plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
}

android {


    namespace = "com.kuri2024.loasearch"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kuri2024.loasearch"
        minSdk = 24
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
    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
//    dependencies{
//        classpath ('com.google.gms:google-services:4.3.10')
//    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Retrofit: 서버와 통신
    implementation(libs.retrofit)
    implementation (libs.converter.gson)
    // Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)
    implementation(kotlin("script-runtime"))

//    jsoup
    implementation (libs.jsoup)

//    mvvm
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.jetbrains.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

//     카카오 로그인 API 모듈
    implementation (libs.v2.user)

//    파이어베이스
    implementation(platform(libs.firebase.bom))
    implementation (platform(libs.firebase.bom.v3210))
    implementation (libs.firebase.auth.ktx)
    implementation (libs.play.services.auth)
    implementation(libs.firebase.database)
}