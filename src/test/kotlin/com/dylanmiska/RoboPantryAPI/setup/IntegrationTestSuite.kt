package com.dylanmiska.RoboPantryAPI.setup

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTestSuite {
    companion object {
        val instance: PostgreSQLContainer<*> by lazy {
            PostgreSQLContainer<Nothing>("postgres:15.3").apply {
                withDatabaseName("testdb")
                withUsername("testuser")
                withPassword("testpassword")
                withExposedPorts(5432)
                start()
            }
        }

        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", instance::getJdbcUrl)
            registry.add("spring.datasource.username", instance::getUsername)
            registry.add("spring.datasource.password", instance::getPassword)
        }
    }
}
