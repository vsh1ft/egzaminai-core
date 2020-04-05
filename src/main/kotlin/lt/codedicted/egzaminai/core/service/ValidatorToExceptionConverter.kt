package lt.codedicted.egzaminai.core.service

import lt.codedicted.egzaminai.core.exception.InvalidTypeException
import javax.validation.Validator

class ValidatorToExceptionConverter(private val validator: Validator) {

    fun <T> validate(entity: T) {
        if (validator.validate(entity).isNotEmpty()) {
            throw InvalidTypeException(validator.validate(entity).iterator().next().message)
        }
    }
}
