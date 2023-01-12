package com.sedintechnologies.qa.todoapi.v3.repositories

import com.sedintechnologies.qa.todoapi.v3.models.TodoV3
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface TodoV3Repository:CrudRepository<TodoV3,Long> {
    fun findAllByUsername(username:String):Iterable<TodoV3>;
    fun findByUsernameAndId(username:String,id:Long):Optional<TodoV3>;
}