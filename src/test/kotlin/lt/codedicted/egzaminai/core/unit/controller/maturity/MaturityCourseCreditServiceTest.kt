package lt.codedicted.egzaminai.core.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.core.model.maturity.MaturityCourseCredit
import lt.codedicted.egzaminai.core.repository.maturity.MaturityCourseCreditRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import lt.codedicted.egzaminai.core.service.maturity.MaturityCourseCreditService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MaturityCourseCreditServiceTest {

    @MockK
    private lateinit var repository: MaturityCourseCreditRepository

    @MockK
    private lateinit var validator: ValidatorToExceptionConverter

    private lateinit var controller: MaturityCourseCreditService

    @BeforeEach
    fun setUp() {
        controller = MaturityCourseCreditService(repository, validator)
    }

    @Test
    fun `Retrieves course credits`() {
        val expectedCredits = listOf(MaturityCourseCredit("id", "user", 2020, "url"))
        every { repository.findAll() } returns expectedCredits

        val actualCredits = controller.getCredits()

        assertEquals(expectedCredits, actualCredits)
    }

    @Test
    fun `Saves credit`() {
        val expectedCredit =
            MaturityCourseCredit("id", "user", 2020, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedCredit) } just Runs

        controller.save(expectedCredit)

        verify { repository.save(any()) }
    }

    @Test
    fun `Validates on save`() {
        val expectedCredit =
            MaturityCourseCredit("id", "user", 2020, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedCredit) } just Runs

        controller.save(expectedCredit)

        verify { validator.validate(expectedCredit) }
    }

    @Test
    fun `Updates credit`() {
        val expectedCredit =
            MaturityCourseCredit("id", "user", 2020, "url")
        every { repository.save(expectedCredit) } just Runs
        every { validator.validate(expectedCredit) } just Runs

        controller.update(expectedCredit)

        verify { repository.save(expectedCredit) }
    }

    @Test
    fun `Validates on update`() {
        val expectedCredit =
            MaturityCourseCredit("id", "user", 2020, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedCredit) } just Runs

        controller.update(expectedCredit)

        verify { validator.validate(expectedCredit) }
    }

    @Test
    fun `Deletes credit`() {
        every { repository.deleteById("id") } just Runs

        controller.delete("id")

        verify { repository.deleteById("id") }
    }

}
