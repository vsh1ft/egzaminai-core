package lt.codedicted.egzaminai.core.repository.maturity

import lt.codedicted.egzaminai.core.model.maturity.MaturityProgram
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityProgramRepository  : MongoRepository<MaturityProgram, String> {

    fun save(program: MaturityProgram)

}
