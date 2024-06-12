package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.generics

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals
import java.text.SimpleDateFormat
import java.util.*


abstract class BaseDeserializationTest<T: Any>(private val classType: Class<T>) {

    abstract var jsonObject: String
    abstract var expected: T
    protected lateinit var actual: T
    protected val dateFormat = SimpleDateFormat("dd-MM-yyyy").apply { timeZone = TimeZone.getTimeZone("UTC") }

    fun getActual() {
        actual = ObjectMapper().readValue(jsonObject, classType)
    }

    @Test
    fun standardDeserialization() {
        getActual()
        assertEquals("Deserialization does not equal expected", expected, actual)
    }

}