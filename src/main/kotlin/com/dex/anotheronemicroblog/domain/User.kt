package com.dex.anotheronemicroblog.domain

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @Column(unique = true)
    val login: String,
    @Column
    private val password: String,
    @Column
    val email: String,
    @Column
    @Enumerated(EnumType.STRING)
    val role: UserRole = UserRole.USER,
    @Column
    @Enumerated(EnumType.STRING)
    val status: UserStatus = UserStatus.ON_CONFIRMATION
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return login
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return status == UserStatus.BLOCKED || status == UserStatus.DISABLED
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return status == UserStatus.ACTIVATED || status == UserStatus.ON_CONFIRMATION
    }
}
