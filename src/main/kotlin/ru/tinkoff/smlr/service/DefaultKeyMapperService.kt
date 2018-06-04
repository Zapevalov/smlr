package ru.tinkoff.smlr.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class DefaultKeyMapperService : KeyMapperService {

    @Autowired
    lateinit var converter: KeyConverterService


    private val map: MutableMap<Long, String> = ConcurrentHashMap()
    val sequence = AtomicLong(10000000L)

    override fun add(link: String):String {
        val id = sequence.getAndIncrement()
        val key = converter.idToKey(id)
        map[id] = link
        return key
    }


    override fun getLink(key: String): KeyMapperService.Get {
        val id = converter.keyToId(key)
        return if (map[id] == null) KeyMapperService.Get.NotFound(key)
        else KeyMapperService.Get.Link(map[id]!!)
    }
}