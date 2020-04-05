package lt.codedicted.egzaminai.core.unit.controller.pupp

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.core.model.pupp.PuppExam
import lt.codedicted.egzaminai.core.model.types.PuppExamName
import lt.codedicted.egzaminai.core.repository.pupp.PuppExamRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import lt.codedicted.egzaminai.core.service.pupp.PuppExamService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PuppExamServiceTest {

    @MockK
    private lateinit var repository: PuppExamRepository

    @MockK
    private lateinit var validator: ValidatorToExceptionConverter

    private lateinit var controller: PuppExamService

    @BeforeEach
    fun setUp() {
        controller = PuppExamService(repository, validator)
    }

    @Test
    fun `Retrieves exams`() {
        val expectedExams = listOf(PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url"))
        every { repository.findAll() } returns expectedExams

        val actualExams = controller.getExams()

        assertEquals(expectedExams, actualExams)
    }

    @Test
    fun `Saves exam`() {
        val expectedExam =
            PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.save(expectedExam)

        verify { repository.save(any()) }
    }

    @Test
    fun `Validates on save`() {
        val expectedExam =
            PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.save(expectedExam)

        verify { validator.validate(expectedExam) }
    }

    @Test
    fun `Updates exam`() {
        val expectedExam =
            PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url")
        every { repository.save(expectedExam) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.update(expectedExam)

        verify { repository.save(expectedExam) }
    }

    @Test
    fun `Validates on update`() {
        val expectedExam =
            PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.update(expectedExam)

        verify { validator.validate(expectedExam) }
    }

    @Test
    fun `Deletes exam`() {
        every { repository.deleteById("id") } just Runs

        controller.delete("id")

        verify { repository.deleteById("id") }
    }

}
