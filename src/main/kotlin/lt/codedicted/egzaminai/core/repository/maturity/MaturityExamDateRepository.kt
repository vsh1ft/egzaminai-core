package lt.codedicted.egzaminai.core.repository.maturity

import lt.codedicted.egzaminai.core.model.maturity.MaturityExamDate
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityExamDateRepository  : MongoRepository<MaturityExamDate, String> {

    fun save(date: MaturityExamDate)

}
