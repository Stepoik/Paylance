plugins {
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core)
        }
    }
}

android {
    namespace = "stepan.gorokhov.paylance.coreui"
}