package com.dex.anotheronemicroblog.repository

import com.dex.anotheronemicroblog.domain.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Int>