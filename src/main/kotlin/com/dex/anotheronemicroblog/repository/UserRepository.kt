package com.dex.anotheronemicroblog.repository

import com.dex.anotheronemicroblog.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int>
{
    fun findByLoginAndPassword(login: String, password: String) : User?
}