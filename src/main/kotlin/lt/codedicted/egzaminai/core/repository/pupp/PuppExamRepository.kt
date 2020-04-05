package lt.codedicted.egzaminai.core.repository.pupp

import lt.codedicted.egzaminai.core.model.pupp.PuppExam
import org.springframework.data.mongodb.repository.MongoRepository

interface PuppExamRepository: MongoRepository<PuppExam, String> {

    fun save(exam: PuppExam)

}

