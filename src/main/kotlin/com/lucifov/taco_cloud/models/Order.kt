package com.lucifov.taco_cloud.models

import org.hibernate.validator.constraints.CreditCardNumber
import java.util.Date
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Entity
@Table(name = "Taco_Order")
data class Order(@Id
                 @GeneratedValue(strategy = GenerationType.IDENTITY)
                 @Column(name = "id")
                 var id: Long = -1,

                 @get: NotBlank(message = "{name.required}")
                 @Column(name = "deliveryName")
                 var deliveryName: String = "",

                 @get: NotBlank(message = "{street.required}")
                 @Column(name = "deliveryStreet")
                 var deliveryStreet: String = "",

                 @get: NotBlank(message = "{city.required}")
                 @Column(name = "deliveryCity")
                 var deliveryCity: String = "",

                 @get: NotBlank(message = "{state.required}")
                 @Column(name = "deliveryState")
                 var deliveryState: String = "",

                 @get: NotBlank(message = "{zipCode.required}")
                 @Column(name = "deliveryZip")
                 var deliveryZip: String = "",

                 @get: CreditCardNumber(message = "{creditCard.invalidNumber}")
                 @Column(name = "ccNumber")
                 var ccNumber: String = "",

                 @get: Pattern(regexp = "^((0[1-9])|(1[0-2]))([\\\\/])([1-9][0-9])$",
                         message = "{creditCard.patternMsg}")
                 @Column(name = "ccExpiration")
                 var ccExpiration: String = "",

                 @get: Digits(integer = 3, fraction = 0, message = "{creditCard.invalidCvv}")
                 @Column(name = "ccCVV")
                 var ccCVV: String = "",

                 @ManyToMany(targetEntity = Taco::class)
                 @JoinTable(name = "Taco_Order_Tacos", joinColumns = [JoinColumn(name = "tacoOrderId")], inverseJoinColumns = [JoinColumn(name="tacoId")])
                 var tacos: MutableList<Taco> = mutableListOf(),

                 @Column(name = "placedAt")
                 var placedAt: Date = Date()) {


    @PrePersist
    fun prepersist() {
        placedAt = Date()
    }
}