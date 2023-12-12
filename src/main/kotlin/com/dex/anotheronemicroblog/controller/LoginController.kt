package com.dex.anotheronemicroblog.controller

import com.dex.anotheronemicroblog.domain.User
import com.dex.anotheronemicroblog.domain.dto.TokensDTO
import com.dex.anotheronemicroblog.domain.dto.UserLoginDTO
import com.dex.anotheronemicroblog.security.JwtTokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class LoginController(
    val tokenService: JwtTokenProvider
) {
    @PostMapping
    fun login(@RequestBody loginData: UserLoginDTO): ResponseEntity<TokensDTO>
    {
        return ResponseEntity.ok(tokenService.generateTokens(loginData.login, loginData.password))
    }


    @PostMapping("/check/{token}")
    fun check(@PathVariable token: String): ResponseEntity<User>
    {
        return ResponseEntity.ok(tokenService.check(token))
    }
}