package com.dex.anotheronemicroblog.repository

import com.dex.anotheronemicroblog.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Int>