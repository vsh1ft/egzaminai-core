package lt.codedicted.egzaminai.core.model.pupp

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import org.springframework.data.annotation.Id

data class PuppProgram(

    @Id val id: String,

    @field:Length(min = 1, max = 100)
    val name: String,

    @field:URL
    val programUrl: String
)
