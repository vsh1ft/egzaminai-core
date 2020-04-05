package lt.codedicted.egzaminai.core.unit.controller.pupp

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.core.model.pupp.PuppProgram
import lt.codedicted.egzaminai.core.repository.pupp.PuppProgramRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import lt.codedicted.egzaminai.core.service.pupp.PuppProgramService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PuppProgramServiceTest {

    @MockK
    private lateinit var repository: PuppProgramRepository

    @MockK
    private lateinit var validator: ValidatorToExceptionConverter

    private lateinit var controller: PuppProgramService

    @BeforeEach
    fun setUp() {
        controller = PuppProgramService(repository, validator)
    }

    @Test
    fun `Retrieves programs`() {
        val expectedPrograms = listOf(PuppProgram("id", "user", "url"))
        every { repository.findAll() } returns expectedPrograms

        val actualPrograms = controller.getPrograms()

        assertEquals(expectedPrograms, actualPrograms)
    }

    @Test
    fun `Saves program`() {
        val expectedProgram =
            PuppProgram("id", "user", "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.save(expectedProgram)

        verify { repository.save(any()) }
    }

    @Test
    fun `Validates on save`() {
        val expectedProgram =
            PuppProgram("id", "user", "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.save(expectedProgram)

        verify { validator.validate(expectedProgram) }
    }

    @Test
    fun `Updates program`() {
        val expectedProgram =
            PuppProgram("id", "user", "url")
        every { repository.save(expectedProgram) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.update(expectedProgram)

        verify { repository.save(expectedProgram) }
    }

    @Test
    fun `Validates on update`() {
        val expectedProgram =
            PuppProgram("id", "user", "url")
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
