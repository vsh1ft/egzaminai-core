package lt.codedicted.egzaminai.core.repository

import lt.codedicted.egzaminai.core.model.DatabaseVersion
import org.springframework.data.mongodb.repository.MongoRepository

interface DatabaseVersionRepository : MongoRepository<DatabaseVersion, String> {

    fun save(db: DatabaseVersion)

}
