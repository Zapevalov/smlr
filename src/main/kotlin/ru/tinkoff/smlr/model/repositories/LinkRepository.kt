package ru.tinkoff.smlr.model.repositories

import org.springframework.data.repository.Repository
import ru.tinkoff.smlr.model.Link
import java.util.*


interface LinkRepository: Repository<Link, Long> {
    fun findById(id: Long?): Optional<Link>
    fun save(link: Link): Link
    fun findAll(): List<Link>
}