package ru.tinkoff.smlr.service

import org.springframework.stereotype.Component

@Component
class DefaultKeyConverterService : KeyConverterService {
    val chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray()
    private val charToLong: Map<Char, Long> = (0 until chars.size)
            .map { i -> Pair(chars[i], i.toLong()) }
            .toMap()

    override fun idToKey(id: Long): String {
        var n = id
        val builder = StringBuilder()
        while (n != 0L) {
            builder.append(chars[(n % chars.size).toInt()])
            n /= chars.size
        }
        return builder.reversed().toString()
    }

    override fun keyToId(key: String) = key
            .map { c -> charToLong[c]!! }
            .fold(0L, {a,b -> a * chars.size + b})
}