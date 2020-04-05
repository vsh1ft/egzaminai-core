package lt.codedicted.egzaminai.core.repository.maturity

import lt.codedicted.egzaminai.core.model.maturity.MaturityCourseCredit
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityCourseCreditRepository  : MongoRepository<MaturityCourseCredit, String> {

    fun save(credit: MaturityCourseCredit)

}
