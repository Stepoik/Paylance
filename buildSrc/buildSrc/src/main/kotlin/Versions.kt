object Versions {
    object Android {
        const val version = "8.5.2"
        const val compileSdk = 35
        const val targetSdk = 35
        const val minSdk = 28
    }

    object Application {
        const val versionCode = 1
        const val versionName = "1.0"
    }

    object Kotlin {
        const val version = "2.1.10"

        object Coroutines {
            const val version = "1.9.0"
        }

        object Serialization {
            const val version = Kotlin.version
        }
    }

    object Compose {
        const val version = "1.7.1"

        object Compiler {
            const val version = Kotlin.version
        }

        object Collections {
            const val version = "0.3.8"
        }

        object ViewModel {
            const val version = "2.8.3"
        }

        object Navigation {
            const val version = "2.8.0-alpha09"
        }
    }

    object Koin {
        const val version = "4.0.0"
    }

    object Ktor {
        const val version = "2.3.12"
    }

    object Datastore {
        const val version = "1.1.1"
    }

    object Coil {
        const val version = "3.1.0"
    }

    object Room {
        const val version = "2.7.0-alpha10"

        object Sqlite {
            const val version = "2.5.0-alpha04"
        }
    }

    object KSP {
        const val version = "2.1.10-1.0.29"
    }

    object Firebase {
        const val version = "1.9.0"
    }

    object Google {
        object GMS {
            const val version = "4.4.1"
        }
    }
}