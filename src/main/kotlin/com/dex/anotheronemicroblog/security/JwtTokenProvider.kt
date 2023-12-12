package com.dex.anotheronemicroblog.security

import com.dex.anotheronemicroblog.domain.User
import com.dex.anotheronemicroblog.domain.dto.TokensDTO
import com.dex.anotheronemicroblog.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class JwtTokenProvider(
    @Value("\${spring.security.jwt.secret}")
    private val jwtSecret: String,
    @Value("\${spring.security.jwt.access.expiretion-time}")
    private val accessLifeTime: Long,
    @Value("\${spring.security.jwt.refresh.expiration-time}")
    private val refreshLifeTime: Long,
    @Value("\${spring.security.jwt.prefix}")
    private val tokenPrefix: String,
    private val userService: UserService
) {

    private var parser = Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode((jwtSecret))))
        .build()

    fun createToken(user: User, refresh: Boolean): String {
        return Jwts.builder()
            .claims()
            .subject(user.login)
            .add("id", user.id)
            .add("status", user.status)
            .add("role", user.role)
            .add("refresh", refresh)
            .and()
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(Date().time + TimeUnit.HOURS.toMillis(if (refresh) accessLifeTime else refreshLifeTime)))
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode((jwtSecret))))
            .compact()
    }

    fun extractUserFromToken(token: String): User {
        val tokenExtracted = token.replace(tokenPrefix,"")
        val claims = parser.parseSignedClaims(tokenExtracted)
            .payload
        val id = claims["id"] as Int
        return userService.getById(id) ?: throw RuntimeException("User not found")
    }

    fun generateTokens(login: String, password: String) : TokensDTO
    {
        val user = userService.getByCredentials(login, password)
        return TokensDTO(createToken(user,true),createToken(user,false))
    }
    fun check(token: String): User
    {
        return extractUserFromToken(tokenPrefix+token)
    }

    fun validateToken(tokenHeader: String): Boolean {
        val claims = parser.parseSignedClaims(tokenHeader.replace(tokenPrefix,""))
            .payload
        return claims.expiration.after(Date(System.currentTimeMillis()))
    }
}