package lt.codedicted.egzaminai.core.model.pupp

import lt.codedicted.egzaminai.core.model.types.PuppExamName
import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import javax.validation.constraints.Size

data class PuppExamDate(

    @Id val id: String,

    val name: PuppExamName,

    @field:Size(min = 1, max = 10)
    val color: String,

    val dateTime: LocalDateTime
)
