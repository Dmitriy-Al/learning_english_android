plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Поддержка ksp
    id("com.google.devtools.ksp")
}

android {
    namespace = "aldmitry.dev.learningenglish"
    compileSdk = 33

    defaultConfig {
        applicationId = "aldmitry.dev.learningenglish"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Библиотека для Jetpack Compose, в составе есть AppCompatTheme — удобная обёртка для вкладок структуры AppCompat
    implementation("com.google.accompanist:accompanist-pager:0.19.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.19.0")

    /*  В “navigation-compose” каждое место назначения — это объект типа NavBackStackEntry, а NavBackStackEntry в свою очередь является владельцем Lifecycle,
    ViewModelStore и SavedStateRegistry. */
    val nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // room
    implementation("androidx.room:room-ktx:2.5.1")

    // ksp
    ksp("androidx.room:room-compiler:2.5.2")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}