package ru.tinkoff.smlr.model

import javax.persistence.*

@Entity
@Table(name = "Links")
data class Link(
        var text: String = "",
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0)