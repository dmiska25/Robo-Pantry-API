package com.dylanmiska.RoboPantryAPI.config.spring

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories("com.dylanmiska.RoboPantryAPI")
class config {
}