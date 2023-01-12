package com.sedintechnologies.qa.todoapi.v3.models.requestBodies

import com.sedintechnologies.qa.todoapi.v3.models.TodoStatus
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@Schema(description = "Create Todo Body")
class CreateTodoBody(
        @NotNull @NotEmpty @NotBlank val title:String,
        var status: TodoStatus?
)