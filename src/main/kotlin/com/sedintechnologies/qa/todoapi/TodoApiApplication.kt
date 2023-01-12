package com.sedintechnologies.qa.todoapi

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class TodoApiApplication{
	@Bean
	fun v3Api(): GroupedOpenApi {
		val paths = arrayOf("/v3/**")
		val packagesToscan = arrayOf("com.sedintechnologies.qa.todoapi.v3")
		return GroupedOpenApi.builder().group("v3").pathsToMatch(*paths).packagesToScan(*packagesToscan)
				.build()
	}

}
fun main(args: Array<String>) {
	runApplication<TodoApiApplication>(*args)
}
