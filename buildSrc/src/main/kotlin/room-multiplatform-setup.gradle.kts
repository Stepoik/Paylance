plugins {
    kotlin("multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp")
    id("androidx.room")
    id("com.android.library")
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(Dependencies.Room.roomRuntime)
            implementation(Dependencies.Room.sqliteBundled)
            implementation(Dependencies.Room.sqlite)
        }
    }
}

dependencies {
    add("kspAndroid", Dependencies.Room.roomCompiler)
    add("kspIosSimulatorArm64", Dependencies.Room.roomCompiler)
    add("kspIosX64", Dependencies.Room.roomCompiler)
    add("kspIosArm64", Dependencies.Room.roomCompiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}