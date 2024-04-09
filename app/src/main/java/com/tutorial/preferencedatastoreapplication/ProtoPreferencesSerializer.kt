package com.tutorial.preferencedatastoreapplication

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by Chandan Jana on 30-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
object ProtoPreferencesSerializer : Serializer<ProtoPreferences> {
    override val defaultValue: ProtoPreferences
            = ProtoPreferences.getDefaultInstance()

    override suspend fun readFrom(
        input: InputStream
    ): ProtoPreferences{
        try {
            return ProtoPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: ProtoPreferences,
        output: OutputStream
    ) = t.writeTo(output)
}