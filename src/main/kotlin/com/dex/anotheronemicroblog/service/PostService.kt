package com.dex.anotheronemicroblog.service

import com.dex.anotheronemicroblog.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val repository: PostRepository
)