package ru.tinkoff.smlr.services

import junit.framework.Assert.assertEquals
import org.junit.Test
import ru.tinkoff.smlr.service.DefaultKeyConverterService
import ru.tinkoff.smlr.service.KeyConverterService
import java.util.*

class DefaultKeyConverterServiceTest {
    private val service:KeyConverterService = DefaultKeyConverterService()

    @Test fun givenIdMustBeConvertableBothWays(){

        for (i in 0 .. 1000L){
            val initialId = Math.abs(Random().nextLong())
            val key = service.idToKey(initialId)
            val id = service.keyToId(key)
            assertEquals(initialId, id)
        }
    }
}