package com.dex.anotheronemicroblog.security

import com.dex.anotheronemicroblog.domain.UserRole
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val tokenProvider: JwtAuthenticationFilter
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
//            .sessionManagement { item -> item.disable() }
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers("/users/create").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/users/all").hasAnyRole(UserRole.ADMIN.name, UserRole.MODERATOR.name)
                    .requestMatchers("/login/check").hasAnyRole(UserRole.ADMIN.name, UserRole.MODERATOR.name)
                    .anyRequest().authenticated()
            }
            .addFilterBefore(tokenProvider, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }


}