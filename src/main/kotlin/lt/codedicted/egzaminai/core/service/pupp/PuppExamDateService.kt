package lt.codedicted.egzaminai.core.service.pupp

import lt.codedicted.egzaminai.core.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.core.repository.pupp.PuppExamDateRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import java.util.*


class PuppExamDateService(private val repository: PuppExamDateRepository,
                          private val validator: ValidatorToExceptionConverter
) {

    fun getDates(): Collection<PuppExamDate> {
        return repository.findAll()
    }

    fun save(date: PuppExamDate) {
        validator.validate(date)
        repository.save(date.copy(id = UUID.randomUUID().toString()))
    }

    fun update(date: PuppExamDate) {
        validator.validate(date)
        repository.save(date)
    }

    fun delete(dateId: String) {
        repository.deleteById(dateId)
    }

}
