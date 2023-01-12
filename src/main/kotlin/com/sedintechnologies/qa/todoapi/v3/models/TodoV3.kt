package com.sedintechnologies.qa.todoapi.v3.models

import com.sedintechnologies.qa.todoapi.v3.models.responseBodies.TodoResponseBody
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.Date
@Entity(name = "TODOV3")
class TodoV3(
    val username:String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?,
    var title:String, var status: TodoStatus, var createdAt:Date, var updatedAt:Date
) {
    fun toResponseBody():TodoResponseBody{
        return TodoResponseBody(this.id!!,this.title,this.status,this.createdAt,this.updatedAt)
    }
}