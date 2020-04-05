package lt.codedicted.egzaminai.core.repository.pupp

import lt.codedicted.egzaminai.core.model.pupp.PuppExamDate
import org.springframework.data.mongodb.repository.MongoRepository

interface PuppExamDateRepository: MongoRepository<PuppExamDate, String> {

    fun save(exam: PuppExamDate)

}


