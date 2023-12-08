package com.dex.anotheronemicroblog.service

import com.dex.anotheronemicroblog.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
)