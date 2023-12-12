package com.dex.anotheronemicroblog.controller

import com.dex.anotheronemicroblog.domain.User
import com.dex.anotheronemicroblog.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService
) {
    @PostMapping("/create")
    fun create(@RequestBody user: User)
    {
        service.create(user)
    }

    @GetMapping("/all")
    fun getAll(): ResponseEntity<List<User>>
    {
        return ResponseEntity.ok().body(service.getAll())
    }
}