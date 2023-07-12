package com.sedintechnologies.qa.todoapi.v3.controllers
import com.sedintechnologies.qa.todoapi.v3.models.TodoStatus
import com.sedintechnologies.qa.todoapi.v3.models.TodoV3
import com.sedintechnologies.qa.todoapi.v3.models.requestBodies.CreateTodoBody
import com.sedintechnologies.qa.todoapi.v3.models.requestBodies.PatchTodoBody
import com.sedintechnologies.qa.todoapi.v3.models.requestBodies.PutTodoBody
import com.sedintechnologies.qa.todoapi.v3.models.responseBodies.TodoResponseBody
import com.sedintechnologies.qa.todoapi.v3.repositories.TodoV3Repository
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*
@Tag(name = "Todo", description = "Todo Api with v2 version")
@RestController()
@RequestMapping("v3/todo")
@SecurityRequirement(name = "jwtAuth")
class TodoControllerV3(private val todoV3Repository: TodoV3Repository) {
    @GetMapping
    fun index():Iterable<TodoResponseBody> {
        return todoV3Repository.findAll().map { it.toResponseBody()}
    }
    @GetMapping("/{id}")
    fun a(@PathVariable id:Long): TodoResponseBody {
        val gotTodo = todoV3Repository.findById(id)
        if (gotTodo.isPresent){
            return gotTodo.get().toResponseBody()
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"Todo with Id "+id+ "Not found")
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@Valid @RequestBody createTodoBody: CreateTodoBody, @AuthenticationPrincipal userDetails: UserDetails): TodoResponseBody {
        val todoV2 = TodoV3(userDetails.username,
            null,createTodoBody.title,if(createTodoBody.status!=null) createTodoBody.status!! else TodoStatus.ACTIVE,Date(),Date())
        todoV3Repository.save(todoV2)
        return todoV2.toResponseBody()
    }
    @PutMapping("/{id}")
    fun put(@Valid @RequestBody putTodoBody: PutTodoBody, @PathVariable id:Long): TodoResponseBody {
        val gotTodoO = todoV3Repository.findById(id)
        if (gotTodoO.isPresent){
            val gotTodo = gotTodoO.get()
            gotTodo.title = putTodoBody.title
            gotTodo.status = putTodoBody.status
            gotTodo.updatedAt = Date()
            todoV3Repository.save(gotTodo)
            return gotTodo.toResponseBody()
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"Todo with Id "+id+ "Not found")
        }
    }
    @PatchMapping("/{id}")
    fun patch(@Valid @RequestBody patchTodoBody: PatchTodoBody, @PathVariable id:Long): TodoResponseBody {
        val gotTodoO = todoV3Repository.findById(id)
        if (gotTodoO.isPresent){
            val gotTodo = gotTodoO.get()
            if(!patchTodoBody.title.isNullOrEmpty() && patchTodoBody.title.isNotBlank())
                gotTodo.title = patchTodoBody.title
            if(patchTodoBody.status != null)
                gotTodo.status = patchTodoBody.status!!
            gotTodo.updatedAt = Date()
            todoV3Repository.save(gotTodo)
            return gotTodo.toResponseBody()
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"Todo with Id "+id+ "Not found")
        }
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long){
        val gotTodoO = todoV3Repository.findById(id)
        if (gotTodoO.isPresent){
            val gotTodo = gotTodoO.get()
            todoV3Repository.delete(gotTodo)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"Todo with Id "+id+ "Not found")
        }
    }
}