package com.dex.anotheronemicroblog.service

import com.dex.anotheronemicroblog.domain.User
import com.dex.anotheronemicroblog.repository.UserRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
)
{
    fun create(user: User)
    {
        repository.save(user)
    }

    fun getAll(): List<User> {
        return repository.findAll()
    }

    fun getById(id: Int): User? {
        return repository.findById(id).orElse(null)
    }

    fun getByCredentials(login: String, password: String): User
    {
        return repository.findByLoginAndPassword(login, password) ?: throw BadCredentialsException("Wrong login or password")
    }
}