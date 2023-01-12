package com.sedintechnologies.qa.todoapi.v3.models.requestBodies

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class CreateUserBody(@NotNull @NotEmpty @NotBlank val username:String, @NotNull @NotEmpty @NotBlank val password:String)