package com.dylanmiska.RoboPantryAPI

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration


@SpringBootApplication
@Configuration
class RoboPantryApiApplication

fun main(args: Array<String>) {
	runApplication<RoboPantryApiApplication>(*args)
}