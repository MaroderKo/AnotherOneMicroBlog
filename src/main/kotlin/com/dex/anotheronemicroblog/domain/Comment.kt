package com.dex.anotheronemicroblog.domain

import jakarta.persistence.*

@Entity
data class Comment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @JoinColumn
    @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY, optional = false)
    val author: User,
    @Column(nullable = false, scale = 1000)
    val text: String
)