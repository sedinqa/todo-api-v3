package com.sedintechnologies.qa.todoapi

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
class Config {
    @Bean
    fun users(): InMemoryUserDetailsManager {
        return InMemoryUserDetailsManager()
    }
}