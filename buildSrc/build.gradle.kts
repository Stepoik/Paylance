plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(Dependencies.Android.Library.gradlePlugin)
    implementation(Dependencies.Kotlin.gradlePlugin)
    implementation(Dependencies.Kotlin.Serialization.gradlePlugin)
    implementation(Dependencies.Compose.gradlePlugin)
    implementation(Dependencies.Compose.Compiler.gradlePlugin)
    implementation(Dependencies.KSP.gradlePlugin)
    implementation(Dependencies.Room.gradlePlugin)
    implementation(Dependencies.Google.GMS.gradlePlugin)
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}