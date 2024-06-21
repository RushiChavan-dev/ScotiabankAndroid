import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.scotiabank.assignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.scotiabank.assignment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localProperties.load(localPropertiesFile.inputStream())

            localProperties["api_key"]?.let {
                buildConfigField("String", "API_KEY", "\"$it\"")
            }
            localProperties["base_url"]?.let {
                buildConfigField("String", "BASE_URL", "\"$it\"")
            }
        }
    }


    testOptions {
        unitTests.all {
            it.jvmArgs("-noverify")
        }
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging  {
        resources {
            excludes += "META-INF/DEPENDENCIES"
            excludes += "/..."
            excludes  +=   "META-INF/LICENSE.md"
            excludes  +=  "META-INF/LICENSE"
            excludes  +=  "META-INF/LICENSE.txt"
            excludes  +=  "META-INF/NOTICE"
            excludes  +=  "META-INF/NOTICE.txt"
            excludes  +=  "META-INF/DEPENDENCIES"
            excludes  +=  "META-INF/DEPENDENCIES.txt"
            excludes  +=  "META-INF/INDEX.LIST"
            excludes  +=  "META-INF/ASL2.0"

            exclude("META-INF/DEPENDENCIES")
            exclude("META-INF/LICENSE.md")
            exclude("META-INF/LICENSE")
            exclude("META-INF/LICENSE.txt")
            exclude("META-INF/NOTICE")
            exclude("META-INF/NOTICE.txt")
            exclude("META-INF/DEPENDENCIES.txt")
            exclude("META-INF/INDEX.LIST")
            exclude("META-INF/ASL2.0")
            exclude("META-INF/LICENSE-notice.md")
        }
    }






}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.ui.test.junit4.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    debugImplementation(libs.androidx.ui.tooling)
    testImplementation(kotlin("test"))
    testImplementation (libs.robolectric)




    testImplementation ("io.mockk:mockk-android:1.13.11")
    testImplementation ("io.mockk:mockk-agent:1.13.11")
    androidTestImplementation ( "io.mockk:mockk-android:1.13.11")
    androidTestImplementation ("io.mockk:mockk-agent:1.13.11")




    // Lifecycle components
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose UI toolkit
    implementation(libs.androidx.compose.bom)
    testImplementation ("io.mockk:mockk-jvm:1.13.2")



    // Compose UI testing dependencies
    androidTestImplementation(libs.androidx.compose.bom)
    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Foundation modules for Compose
    implementation(libs.androidx.foundation)






    // Activity Compose integration
    implementation(libs.androidx.activity.compose)

    // Core AndroidX libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Work Manager
    implementation(libs.androidx.work.runtime.ktx)

    // Material Design components
    implementation(libs.material)

    // OkHttp logging interceptor
    implementation(libs.logging.interceptor)

    // Unit testing dependencies

    testImplementation(libs.mockwebserver)

    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.hilt.navigation.compose)
    kaptAndroidTest(libs.androidx.hilt.compiler)

    implementation(libs.androidx.ui.test.android)

    implementation(libs.androidx.monitor)

    // For instrumented tests
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.fragment.testing)


    implementation(libs.retrofit)
    implementation(libs.converter.gson)


    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)


    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.hilt.navigation.fragment)
    kapt(libs.androidx.hilt.compiler)

    // Glide integration for Compose
    implementation(libs.compose)

    // Compose animation module
    implementation(libs.androidx.animation)
    implementation(libs.androidx.compose.material3.material3)


    // Core library
    androidTestImplementation (libs.androidx.core )
    // JUnit4 Rules
    androidTestImplementation (libs.androidx.rules)
    // JUnit4 Runner
    androidTestImplementation (libs.androidx.runner )

    // Espresso
    androidTestImplementation (libs.androidx.espresso.core)
    // Compose Testing
    androidTestImplementation (libs.androidx.compose.ui.ui.test.junit4   )
    debugImplementation        ( libs.androidx.compose.ui.ui.tooling                     )
    debugImplementation        ( libs.androidx.compose.ui.ui.test.manifest              )



}