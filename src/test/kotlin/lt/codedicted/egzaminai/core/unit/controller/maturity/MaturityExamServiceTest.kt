package lt.codedicted.egzaminai.core.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.core.model.maturity.MaturityExam
import lt.codedicted.egzaminai.core.model.types.ExamName
import lt.codedicted.egzaminai.core.model.types.ExamType
import lt.codedicted.egzaminai.core.repository.maturity.MaturityExamRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import lt.codedicted.egzaminai.core.service.maturity.MaturityExamService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MaturityExamServiceTest {

    @MockK
    private lateinit var repository: MaturityExamRepository

    @MockK
    private lateinit var validator: ValidatorToExceptionConverter

    private lateinit var controller: MaturityExamService

    @BeforeEach
    fun setUp() {
        controller = MaturityExamService(repository, validator)
    }

    @Test
    fun `Retrieves exams`() {
        val expectedExams =
            listOf(MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url"))
        every { repository.findAll() } returns expectedExams

        val actualExams = controller.getExams()

        assertEquals(expectedExams, actualExams)
    }

    @Test
    fun `Saves exam`() {
        val expectedExam =
            MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.save(expectedExam)

        verify { repository.save(any()) }
    }

    @Test
    fun `Validates on save`() {
        val expectedExam =
            MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.save(expectedExam)

        verify { validator.validate(expectedExam) }
    }

    @Test
    fun `Updates exam`() {
        val expectedExam =
            MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url")
        every { repository.save(expectedExam) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.update(expectedExam)

        verify { repository.save(expectedExam) }
    }

    @Test
    fun `Validates on update`() {
        val expectedExam =
            MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url")
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
