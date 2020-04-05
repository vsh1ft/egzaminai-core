package lt.codedicted.egzaminai.core.service.pupp

import lt.codedicted.egzaminai.core.model.pupp.PuppExam
import lt.codedicted.egzaminai.core.repository.pupp.PuppExamRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import java.util.*

class PuppExamService(private val repository: PuppExamRepository,
                      private val validator: ValidatorToExceptionConverter
) {

    fun getExams(): Collection<PuppExam> {
        return repository.findAll()
    }

    fun save(exam: PuppExam) {
        validator.validate(exam)
        repository.save(exam.copy(id = UUID.randomUUID().toString()))
    }

    fun update(exam: PuppExam) {
        validator.validate(exam)
        repository.save(exam)
    }

    fun delete(puppExamId: String) {
        repository.deleteById(puppExamId)
    }

}
