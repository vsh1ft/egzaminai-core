package lt.codedicted.egzaminai.core.unit.service

import io.mockk.*
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.core.exception.InvalidTypeException
import lt.codedicted.egzaminai.core.model.maturity.MaturityExam
import lt.codedicted.egzaminai.core.model.types.ExamName
import lt.codedicted.egzaminai.core.model.types.ExamType
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import javax.validation.ConstraintViolation
import javax.validation.Validator

@ExtendWith(MockKExtension::class)
class ValidatorToExceptionServiceTest {

    @MockK
    private lateinit var validatorSpy: Validator

    @MockK
    private lateinit var constraintViolationStub: ConstraintViolation<MaturityExam>

    private lateinit var service: ValidatorToExceptionConverter

    @BeforeEach
    fun setUp() {
        service = ValidatorToExceptionConverter(validatorSpy)
    }

    @Test
    fun `Validates entity`() {
        val exam = MaturityExam(
            "123",
            ExamName.ENGLISH_LANGUAGE,
            2017,
            ExamType.NATIONAL_LEVEL,
            "8027_2019_LKL_VBE_PG.pdf",
            "http://www.smth.com/8027_2019_LKL_VBE_PG.pdf"
        )
        every { validatorSpy.validate(exam) } returns emptySet()

        service.validate(exam)

        verify { validatorSpy.validate(exam) }
    }

    @Test
    fun `Throws exception when entity is now valid`() {
        val exam = MaturityExam(
            "123",
            ExamName.ENGLISH_LANGUAGE,
            2017,
            ExamType.NATIONAL_LEVEL,
            "http://www.smth.com/8027_2019_LKL_VBE_PG.pdf",
            "http://www.smth.com/8027_2019_LKL_VBE_PG.pdf"
        )
        every { constraintViolationStub.message } returns "error"
        every { validatorSpy.validate(exam) } returns setOf(constraintViolationStub)

        Assertions.assertThrows(InvalidTypeException::class.java) {
            service.validate(exam)
        }
    }

}
