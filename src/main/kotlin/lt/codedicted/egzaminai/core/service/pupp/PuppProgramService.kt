package lt.codedicted.egzaminai.core.service.pupp

import lt.codedicted.egzaminai.core.model.pupp.PuppProgram
import lt.codedicted.egzaminai.core.repository.pupp.PuppProgramRepository
import lt.codedicted.egzaminai.core.service.ValidatorToExceptionConverter
import java.util.*


class PuppProgramService(
    private val repository: PuppProgramRepository,
    private val validator: ValidatorToExceptionConverter
) {

    fun getPrograms(): Collection<PuppProgram> {
        return repository.findAll()
    }

    fun save(program: PuppProgram) {
        validator.validate(program)
        repository.save(program.copy(id = UUID.randomUUID().toString()))
    }

    fun update(program: PuppProgram) {
        validator.validate(program)
        repository.save(program)
    }

    fun delete(programId: String) {
        repository.deleteById(programId)
    }

}
