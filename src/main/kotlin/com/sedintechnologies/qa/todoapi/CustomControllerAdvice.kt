package com.sedintechnologies.qa.todoapi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class CustomControllerAdvice {
    @ExceptionHandler(HttpMessageNotReadableException::class) // exception handled
    fun handleValidationErrors(
        e: HttpMessageNotReadableException
    ): ResponseEntity<ErrorResponse?>? {
        return ResponseEntity(
            ErrorResponse(
                HttpStatus.BAD_REQUEST,
                e.message
            ),
            HttpStatus.BAD_REQUEST
        )
    }
    @ExceptionHandler(ResponseStatusException::class) // exception handled
    fun handleResponseStatuses(
        e: ResponseStatusException
    ): ResponseEntity<ErrorResponse?>? {
        return ResponseEntity(
            ErrorResponse(
                e.statusCode,
                e.reason
            ),
            e.statusCode
        )
    }

    // fallback method
    @ExceptionHandler(Exception::class) // exception handled
    fun handleExceptions(
        e: Exception
    ): ResponseEntity<ErrorResponse?>? {
        val status = HttpStatus.INTERNAL_SERVER_ERROR // 500
        return ResponseEntity(
            ErrorResponse(
                status,
                e.cause?.message
            ),
            status
        )
    }
}