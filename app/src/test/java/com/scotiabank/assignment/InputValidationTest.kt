package com.scotiabank.assignment

import com.scotiabank.assignment.util.ValidationUtils
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class InputValidationTest {

    @Before
    fun setup() {
        mockkObject(ValidationUtils)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test valid inputs`() {
        val validInputs = listOf("validInput", "valid-Input", "12345", "valid123")

        validInputs.forEach {
            assertTrue(ValidationUtils.isValidInput(it), "Expected input $it to be valid")
        }
    }

    @Test
    fun `test invalid inputs`() {
        val invalidInputs = listOf("invalid input", "-invalid", "toolonginputvaluefortestingpurposesthatisnotvalid", "!invalid", "-invalid-")

        invalidInputs.forEach {
            assertFalse(ValidationUtils.isValidInput(it), "Expected input $it to be invalid")
        }
    }
}
