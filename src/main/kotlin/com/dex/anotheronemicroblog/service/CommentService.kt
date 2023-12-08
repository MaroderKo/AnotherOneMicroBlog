package com.dex.anotheronemicroblog.service

import com.dex.anotheronemicroblog.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val repository: CommentRepository
)