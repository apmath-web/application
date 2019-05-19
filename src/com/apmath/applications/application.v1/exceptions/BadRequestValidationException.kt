package com.apmath.applications.application.v1.exceptions

import com.apmath.validation.simple.ValidatorInterface
import com.apmath.validation.simple.MessageInterface

class BadRequestValidationException(validation: ValidatorInterface) : BadRequestException("Validation error") {
    val messages: MutableList<MessageInterface> = validation.messages
}
