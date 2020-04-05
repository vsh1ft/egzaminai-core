package lt.codedicted.egzaminai.core.service.maturity

import lt.codedicted.egzaminai.core.model.maturity.MaturityExamDate
import lt.codedicted.egzaminai.core.repository.maturity.MaturityExamDateRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import java.util.*

class MaturityExamDateService(private val repository: MaturityExamDateRepository,
                              private val validator: ValidatorToExceptionConverter
) {

    fun getDates(): Collection<MaturityExamDate> {
        return repository.findAll()
    }

    fun save(date: MaturityExamDate) {
        validator.validate(date)
        repository.save(date.copy(id = UUID.randomUUID().toString()))
    }

    fun update(date: MaturityExamDate) {
        validator.validate(date)
        repository.save(date)
    }

    fun delete(dateId: String) {
        repository.deleteById(dateId)
    }

}
