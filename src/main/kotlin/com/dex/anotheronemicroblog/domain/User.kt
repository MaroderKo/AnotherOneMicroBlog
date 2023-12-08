package com.dex.anotheronemicroblog.domain

import jakarta.persistence.*

@Entity(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @Column(unique = true)
    val login: String,
    @Column
    val password: String,
    @Column
    val email: String,
    @Column
    @Enumerated(EnumType.STRING)
    val role: UserRole,
    @Column
    @Enumerated(EnumType.STRING)
    val status: UserStatus
)
