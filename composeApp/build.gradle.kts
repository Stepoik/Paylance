import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id(Plugins.Kotlin.multiplatform)
    id(Plugins.Android.application)
    id(Plugins.Compose.multiplatform)
    id(Plugins.Compose.compiler)
    id(Plugins.Google.gms)
    id(Plugins.Kotlin.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(Dependencies.Ktor.clientOkHttp)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            // Compose
            implementation(Dependencies.Compose.navigation)
            implementation(Dependencies.Compose.viewModel)
            implementation(Dependencies.Compose.immutableCollections)
            // Koin
            implementation(Dependencies.Koin.viewModel)
            implementation(Dependencies.Koin.koinCompose)
            // Network
            implementation(Dependencies.Ktor.serialization)
            implementation(Dependencies.Ktor.contentNegotiation)
            implementation(Dependencies.Ktor.clientCore)

            implementation(Dependencies.Kotlin.datetime)

            implementation(Dependencies.Firebase.auth)

            implementation(projects.coreUi)
            implementation(projects.uikit)
            // Coil
            implementation(Dependencies.Coil.compose)
            implementation(Dependencies.Coil.ktor)
        }

        iosMain.dependencies {
            implementation(Dependencies.Ktor.clientDarwin)
        }
    }
}

android {
    namespace = "stepan.gorokhov.paylance"
    compileSdk = Versions.Android.compileSdk

    defaultConfig {
        applicationId = "stepan.gorokhov.paylance"
        minSdk = Versions.Android.minSdk
        targetSdk =  Versions.Android.targetSdk
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

