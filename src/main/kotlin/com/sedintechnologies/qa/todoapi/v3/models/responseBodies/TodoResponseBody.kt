package com.sedintechnologies.qa.todoapi.v3.models.responseBodies

import com.sedintechnologies.qa.todoapi.v3.models.TodoStatus
import java.util.*
class TodoResponseBody(val id:Long, var title:String, var status: TodoStatus, var createdAt: Date, var updatedAt: Date) {
}