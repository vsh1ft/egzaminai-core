package lt.codedicted.egzaminai.core.service.maturity

import lt.codedicted.egzaminai.core.model.maturity.MaturityExam
import lt.codedicted.egzaminai.core.repository.maturity.MaturityExamRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import java.util.*

class MaturityExamService(
    private val repository: MaturityExamRepository,
    private val validator: ValidatorToExceptionConverter
) {

    fun getExams(): Collection<MaturityExam> {
        return repository.findAll()
    }

    fun save(exam: MaturityExam) {
        validator.validate(exam)
        repository.save(exam.copy(id = UUID.randomUUID().toString()))
    }

    fun update(exam: MaturityExam) {
        validator.validate(exam)
        repository.save(exam)
    }

    fun delete(examId: String) {
        repository.deleteById(examId)
    }

}
