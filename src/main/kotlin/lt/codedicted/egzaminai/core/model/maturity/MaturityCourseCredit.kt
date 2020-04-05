package lt.codedicted.egzaminai.core.model.maturity

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import org.springframework.data.annotation.Id
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class MaturityCourseCredit(

    @Id val id: String,

    @field:Length(min = 1, max = 100)
    val name: String,

    @field:Min(2010)
    @field:Max(2030)
    val year: Int,

    @field:URL
    val creditUrl: String
)
