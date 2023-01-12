package com.sedintechnologies.qa.todoapi

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import java.util.*


class ErrorResponse(httpStatus: HttpStatusCode, val message:String?, val stackTrace: String?=null) {
    val code:Int
    init {
        code = httpStatus.value()
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private val timestamp = Date();
    private var data: Any? = null
}