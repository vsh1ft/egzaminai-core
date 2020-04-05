package lt.codedicted.egzaminai.core.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.core.model.maturity.MaturityProgram
import lt.codedicted.egzaminai.core.model.types.Subject
import lt.codedicted.egzaminai.core.repository.maturity.MaturityProgramRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import lt.codedicted.egzaminai.core.service.maturity.MaturityProgramService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MaturityProgramServiceTest {

    @MockK
    private lateinit var repository: MaturityProgramRepository

    @MockK
    private lateinit var validator: ValidatorToExceptionConverter

    private lateinit var controller: MaturityProgramService

    @BeforeEach
    fun setUp() {
        controller = MaturityProgramService(repository, validator)
    }

    @Test
    fun `Retrieves programs`() {
        val expectedPrograms = listOf(MaturityProgram("", "user", Subject.LITHUANIAN_LANGUAGE, "url"))
        every { repository.findAll() } returns expectedPrograms

        val actualPrograms = controller.getPrograms()

        assertEquals(expectedPrograms, actualPrograms)
    }

    @Test
    fun `Saves program`() {
        val expectedProgram =
            MaturityProgram("", "user", Subject.LITHUANIAN_LANGUAGE, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.save(expectedProgram)

        verify { repository.save(any()) }
    }

    @Test
    fun `Validates on save`() {
        val expectedProgram =
            MaturityProgram("", "user", Subject.LITHUANIAN_LANGUAGE, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.save(expectedProgram)

        verify { validator.validate(expectedProgram) }
    }

    @Test
    fun `Updates program`() {
        val expectedProgram =
            MaturityProgram("", "user", Subject.LITHUANIAN_LANGUAGE, "url")
        every { repository.save(expectedProgram) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.update(expectedProgram)

        verify { repository.save(expectedProgram) }
    }

    @Test
    fun `Validates on update`() {
        val expectedProgram =
            MaturityProgram("", "user", Subject.LITHUANIAN_LANGUAGE, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.update(expectedProgram)

        verify { validator.validate(expectedProgram) }
    }

    @Test
    fun `Deletes program`() {
        every { repository.deleteById("id") } just Runs

        controller.delete("id")

        verify { repository.deleteById("id") }
    }

}
