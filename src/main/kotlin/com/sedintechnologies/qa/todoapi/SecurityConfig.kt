package com.sedintechnologies.qa.todoapi

import com.sedintechnologies.qa.todoapi.v3.security.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig {
    @Autowired
    private lateinit var jwtAuthFilter: JwtAuthenticationFilter
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .csrf().disable()
                .authorizeHttpRequests { authz ->
                    authz
                        .requestMatchers("/error/**","/swagger-ui/**", "/oa/**", "/v3/user","/v3/user/*")
                        .permitAll()
                        .requestMatchers("/v3/todo","/v3/todo/**").authenticated()
                        .and()
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter::class.java)
                }
            .authorizeHttpRequests { authz ->
                authz

            }
        return http.build()
    }
}