package com.lucifov.taco_cloud.models

import org.hibernate.validator.constraints.CreditCardNumber
import java.util.Date
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class Order(val id: Long = -1, val createdAt: Date = Date(),
                 @get: NotBlank(message = "{name.required}")
                 val name: String="",
                 @get: NotBlank(message = "{street.required}")
                 val street: String="",
                 @get: NotBlank(message = "{city.required}")
                 val city: String="",
                 @get: NotBlank(message = "{state.required}")
                 val state: String="",
                 @get: NotBlank(message = "{zipCode.required}")
                 val zip: String="",
                 @get: CreditCardNumber(message = "{creditCard.invalidNumber}")
                 val ccNumber: String="",
                 @get: Pattern(regexp = "^(0[1-9]1[0-2])([\\\\/])([1-9][0-9])\$",
                         message = "{creditCard.patternMsg}")
                 val ccExpiration: String="",
                 @get: Digits(integer = 3, fraction = 0, message = "{creditCard.invalidCvv}")
                 val ccCVV: String="")