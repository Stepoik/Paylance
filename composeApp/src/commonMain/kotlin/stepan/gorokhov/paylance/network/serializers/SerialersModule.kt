package stepan.gorokhov.paylance.network.serializers

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

val paylanceSerializersModule = SerializersModule {
    contextual(LocalDateTime::class, LocalDateTimeSerializer)
}