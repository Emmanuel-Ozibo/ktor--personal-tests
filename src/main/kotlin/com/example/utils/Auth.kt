package com.example.utils

import io.ktor.util.hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object Auth {
    private val hashKey = hex("898748674728934843")

    private val hmacKey = SecretKeySpec(hashKey, "HmacSHA1")

    fun convertToHash(password: String): String {
        val hmac = Mac.getInstance("HmacSHA1")
        hmac.init(hmacKey)
        return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
    }
}
