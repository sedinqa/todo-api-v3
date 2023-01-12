package com.sedintechnologies.qa.todoapi.v3.models.requestBodies

import com.sedintechnologies.qa.todoapi.v3.models.TodoStatus
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class PutTodoBody(@NotNull @NotEmpty @NotBlank val title:String, @NotNull @NotEmpty @NotBlank val status: TodoStatus) {

}