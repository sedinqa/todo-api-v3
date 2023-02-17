package com.sedintechnologies.qa.todoapi.v3.controllers

import com.sedintechnologies.qa.todoapi.v3.models.requestBodies.CreateUserBody
import com.sedintechnologies.qa.todoapi.v3.models.responseBodies.UserResponseBody
import com.sedintechnologies.qa.todoapi.v3.security.JwtService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@Tag(name = "User", description = "User Api with v2 version")
@RestController()
@RequestMapping("v3/user")
class UserController {
    @Autowired
    private lateinit var jwtService:JwtService

    @Autowired
    private lateinit var inMemoryUserDetailsManager: InMemoryUserDetailsManager

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@Valid @RequestBody createUserBody: CreateUserBody):UserResponseBody{
        if(!inMemoryUserDetailsManager.userExists(createUserBody.username)){
            val user = User.builder().username(createUserBody.username).password(createUserBody.password).authorities("USER").build()
            inMemoryUserDetailsManager.createUser(user)
            return UserResponseBody(jwtService.generateToken(user));
        } else {
            throw ResponseStatusException(HttpStatus.FORBIDDEN,"User with provided username already exists")
        }
    }
    @SecurityRequirement(name = "jwtAuth")
    @DeleteMapping
    fun delete(@AuthenticationPrincipal userDetails: UserDetails){
        inMemoryUserDetailsManager.deleteUser(userDetails.username)
    }
    @Throws(Exception::class)
    private fun authenticate(username: String, password: String) :UserDetails{
        val user = inMemoryUserDetailsManager
                .loadUserByUsername(username)
        if(user.password.equals(password) && user.isEnabled){
            return user
        } else {
            throw Exception("INVALID_CREDENTIALS")
        }
    }
    @PostMapping("/login")
    fun login(@Valid @RequestBody createUserBody: CreateUserBody):UserResponseBody{
        val user =authenticate(createUserBody.username,createUserBody.password);

        if(user!=null){
            return UserResponseBody(jwtService.generateToken(user));
        } else {
            throw UsernameNotFoundException(createUserBody.username)
        }
    }
}