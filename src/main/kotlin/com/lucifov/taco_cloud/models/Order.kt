package com.lucifov.taco_cloud.models

import org.hibernate.validator.constraints.CreditCardNumber
import java.util.Date
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

class Order {
    var id: Long = -1

    @get: NotBlank(message = "{name.required}")
    var name: String = ""

    @get: NotBlank(message = "{street.required}")
    var street: String = ""

    @get: NotBlank(message = "{city.required}")
    var city: String = ""

    @get: NotBlank(message = "{state.required}")
    var state: String = ""

    @get: NotBlank(message = "{zipCode.required}")
    var zip: String = ""

    @get: CreditCardNumber(message = "{creditCard.invalidNumber}")
    var ccNumber: String = ""

    @get: Pattern(regexp = "^((0[1-9])|(1[0-2]))([\\\\/])([1-9][0-9])$",
            message = "{creditCard.patternMsg}")
    var ccExpiration: String = ""

    @get: Digits(integer = 3, fraction = 0, message = "{creditCard.invalidCvv}")
    var ccCVV: String = ""
    var designs: ArrayList<Taco> = ArrayList()
    var placedAt: Date = Date()

    override fun toString(): String {
        return """id=$id, name=$name, street=$street, city=$city, state=$state, zip=$zip,
                |cccNumber=$ccNumber, ccExpiration=$ccExpiration, ccCVV=$ccCVV, designs=$designs,
                |placedAt=$placedAt""".trimMargin("|")
    }
}