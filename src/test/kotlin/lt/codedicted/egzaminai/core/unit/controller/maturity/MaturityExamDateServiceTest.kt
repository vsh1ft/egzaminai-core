package lt.codedicted.egzaminai.core.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.core.model.maturity.MaturityExamDate
import lt.codedicted.egzaminai.core.model.types.ExamName
import lt.codedicted.egzaminai.core.model.types.ExamType
import lt.codedicted.egzaminai.core.repository.maturity.MaturityExamDateRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import lt.codedicted.egzaminai.core.service.maturity.MaturityExamDateService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class MaturityExamDateServiceTest {

    @MockK
    private lateinit var repository: MaturityExamDateRepository

    @MockK
    private lateinit var validator: ValidatorToExceptionConverter

    private lateinit var controller: MaturityExamDateService

    @BeforeEach
    fun setUp() {
        controller = MaturityExamDateService(repository, validator)
    }

    @Test
    fun `Retrieves dates`() {
        val expectedDates = listOf(MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL,"" ,LocalDateTime.now()))
        every { repository.findAll() } returns expectedDates

        val actualDates = controller.getDates()

        assertEquals(expectedDates, actualDates)
    }

    @Test
    fun `Saves date`() {
        val expectedDate =
            MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL,"" , LocalDateTime.now())
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedDate) } just Runs

        controller.save(expectedDate)

        verify { repository.save(any()) }
    }

    @Test
    fun `Validates on save`() {
        val expectedDate =
            MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL,"" , LocalDateTime.now())
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedDate) } just Runs

        controller.save(expectedDate)

        verify { validator.validate(expectedDate) }
    }

    @Test
    fun `Updates date`() {
        val expectedDate =
            MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL, "" ,LocalDateTime.now())
        every { repository.save(expectedDate) } just Runs
        every { validator.validate(expectedDate) } just Runs

        controller.update(expectedDate)

        verify { repository.save(expectedDate) }
    }

    @Test
    fun `Validates on update`() {
        val expectedDate =
            MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL,"" , LocalDateTime.now())
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedDate) } just Runs

        controller.update(expectedDate)

        verify { validator.validate(expectedDate) }
    }

    @Test
    fun `Deletes date`() {
        every { repository.deleteById("id") } just Runs

        controller.delete("id")

        verify { repository.deleteById("id") }
    }

}
