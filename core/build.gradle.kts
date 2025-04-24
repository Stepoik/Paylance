plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(Dependencies.Kotlin.datetime)
        }
    }
}

android {
    namespace = "stepan.gorokhov.paylance.core"
}