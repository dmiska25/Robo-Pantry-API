package com.dylanmiska.RoboPantryAPI.config.spring

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@Configuration
@EnableJpaRepositories("com.dylanmiska.RoboPantryAPI")
class config {
//    @Bean
//    fun jacksonObjectMapperCustomization(): Jackson2ObjectMapperBuilderCustomizer? {
//        return Jackson2ObjectMapperBuilderCustomizer { jacksonObjectMapperBuilder: Jackson2ObjectMapperBuilder ->
//            jacksonObjectMapperBuilder.timeZone(
//                TimeZone.getDefault()
//            )
//        }
//    }
//    @PostConstruct
//    fun init() {
//        // Setting Spring Boot SetTimeZone
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
//    }
}