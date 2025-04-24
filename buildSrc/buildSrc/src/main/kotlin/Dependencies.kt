object Dependencies {
    object Android {
        object Library {
            const val gradlePlugin =
                "com.android.library:com.android.library.gradle.plugin:${Versions.Android.version}"
        }
    }

    object Kotlin {
        const val gradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.version}"

        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.Coroutines.version}"

        object Serialization {
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:${Versions.Kotlin.Serialization.version}"
        }
    }

    object Compose {
        const val gradlePlugin =
            "org.jetbrains.compose:compose-gradle-plugin:${Versions.Compose.version}"

        const val immutableCollections = "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.Compose.Collections.version}"
        const val viewModel = "org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.ViewModel.version}"
        const val navigation = "org.jetbrains.androidx.navigation:navigation-compose:${Versions.Compose.Navigation.version}"

        object Compiler {
            const val gradlePlugin =
                "org.jetbrains.kotlin:compose-compiler-gradle-plugin:${Versions.Compose.Compiler.version}"
        }
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.Koin.version}"
        const val koinCompose = "io.insert-koin:koin-compose:${Versions.Koin.version}"
        const val viewModel = "io.insert-koin:koin-compose-viewmodel:${Versions.Koin.version}"
    }

    object Ktor {
        const val clientCore = "io.ktor:ktor-client-core:${Versions.Ktor.version}"
        const val clientOkHttp = "io.ktor:ktor-client-okhttp:${Versions.Ktor.version}"
        const val clientDarwin = "io.ktor:ktor-client-darwin:${Versions.Ktor.version}"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.Ktor.version}"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.Ktor.version}"
    }

    object Datastore {
        const val core = "androidx.datastore:datastore-preferences-core:${Versions.Datastore.version}"
    }

    object Coil {
        const val compose = "io.coil-kt.coil3:coil-compose:${Versions.Coil.version}"
        const val ktor = "io.coil-kt.coil3:coil-network-ktor2:${Versions.Coil.version}"
    }

    object Room {
        const val roomCompiler = "androidx.room:room-compiler:${Versions.Room.version}"
        const val roomRuntime = "androidx.room:room-runtime:${Versions.Room.version}"
        const val sqlite = "androidx.sqlite:sqlite:${Versions.Room.Sqlite.version}"
        const val sqliteBundled = "androidx.sqlite:sqlite-bundled:${Versions.Room.Sqlite.version}"
        const val gradlePlugin = "androidx.room:room-gradle-plugin:${Versions.Room.version}"
    }

    object KSP {
        const val gradlePlugin = "com.google.devtools.ksp:symbol-processing-gradle-plugin:${Versions.KSP.version}"
    }

    object Firebase {
        const val auth = "dev.gitlive:firebase-auth:${Versions.Firebase.version}"
    }

    object Google {
        object GMS {
            const val gradlePlugin = "com.google.gms.google-services:com.google.gms.google-services.gradle.plugin:${Versions.Google.GMS.version}"
        }
    }
}