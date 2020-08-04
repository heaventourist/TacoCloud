package com.lucifov.taco_cloud.models

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class Taco(val id: Long = -1, val createdAt: Date = Date(),
        @get: Size(min=5, message = "Name must be at least 5 characters long")
        @get: NotBlank(message = "{name.required}")
        val name: String = "",
        @get: Size(min=1, message = "You must choose at least 1 ingredient")
        val ingredients: List<String> = emptyList())