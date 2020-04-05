package lt.codedicted.egzaminai.core.service.maturity

import lt.codedicted.egzaminai.core.model.maturity.MaturityCourseCredit
import lt.codedicted.egzaminai.core.repository.maturity.MaturityCourseCreditRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import java.util.*

class MaturityCourseCreditService(private val repository: MaturityCourseCreditRepository,
                                  private val validator: ValidatorToExceptionConverter
) {

    fun getCredits(): Collection<MaturityCourseCredit> {
        return repository.findAll()
    }
    fun save(credit: MaturityCourseCredit) {
        validator.validate(credit)
        repository.save(credit.copy(id = UUID.randomUUID().toString()))
    }
    fun update(credit: MaturityCourseCredit) {
        validator.validate(credit)
        repository.save(credit)
    }

    fun delete(creditId: String) {
        repository.deleteById(creditId)
    }

}
