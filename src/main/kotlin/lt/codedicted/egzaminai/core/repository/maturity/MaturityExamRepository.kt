package lt.codedicted.egzaminai.core.repository.maturity

import lt.codedicted.egzaminai.core.model.maturity.MaturityExam
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityExamRepository: MongoRepository<MaturityExam, String> {

    fun save(exam: MaturityExam)

}
