package lt.codedicted.egzaminai.core.repository.pupp

import lt.codedicted.egzaminai.core.model.pupp.PuppProgram
import org.springframework.data.mongodb.repository.MongoRepository

interface PuppProgramRepository: MongoRepository<PuppProgram, String> {

    fun save(program: PuppProgram)

}


