package com.lucifov.taco_cloud.models

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class Design() {
    @get: Size(min = 5, message = "Name must be at least 5 characters long")
    @get: NotBlank(message = "{name.required}")
    var name: String = ""

    @get: Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: List<String> = emptyList()

    override fun toString(): String {
        return "Name=$name, ingredients=$ingredients"
    }
}