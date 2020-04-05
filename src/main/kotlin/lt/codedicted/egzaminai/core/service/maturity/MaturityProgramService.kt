package lt.codedicted.egzaminai.core.service.maturity

import lt.codedicted.egzaminai.core.model.maturity.MaturityProgram
import lt.codedicted.egzaminai.core.repository.maturity.MaturityProgramRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import java.util.*

class MaturityProgramService(private val repository: MaturityProgramRepository,
                             private val validator: ValidatorToExceptionConverter
) {

    fun getPrograms(): Collection<MaturityProgram> {
        return repository.findAll()
    }

    fun save(program: MaturityProgram) {
        validator.validate(program)
        repository.save(program.copy(id = UUID.randomUUID().toString()))
    }

    fun update(program: MaturityProgram) {
        validator.validate(program)
        repository.save(program)
    }

    fun delete(programId: String) {
        repository.deleteById(programId)
    }

}
