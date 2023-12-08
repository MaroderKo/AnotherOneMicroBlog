package com.dex.anotheronemicroblog.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val annotation: String,
    @Column(nullable = false, scale = 3000)
    val content: String,
    @Column(nullable = false)
    val creationDateTime: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC),
    @Column(nullable = true)
    val editionDateTime: LocalDateTime?,
    @JoinColumn
    @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY, optional = false)
    val author: User,
    @JoinColumn
    @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY, optional = true)
    val editor: User?
)
