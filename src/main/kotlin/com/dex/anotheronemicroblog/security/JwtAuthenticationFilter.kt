package com.dex.anotheronemicroblog.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class JwtAuthenticationFilter(
    val tokenService: JwtTokenProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // get Authorization token from request header if exists
        val tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        // check if token exist and not expired
        if (tokenHeader != null && tokenService.validateToken(tokenHeader)) {
            val user = tokenService.extractUserFromToken(tokenHeader)
            // create new authentication token
            val authToken = UsernamePasswordAuthenticationToken(
                user, null, user.authorities
            )
            authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
            // Create empty SecurityContext and set authentication
            val securityContext = SecurityContextHolder.createEmptyContext()
            securityContext.authentication = authToken
            // Set new SecurityContext
            SecurityContextHolder.setContext(securityContext)
        }
        // move to next filter
        filterChain.doFilter(request, response)

    }

}