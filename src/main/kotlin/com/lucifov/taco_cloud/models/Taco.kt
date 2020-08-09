package com.lucifov.taco_cloud.models

import java.util.*

data class Taco(val id: Long = -1,
                val createdAt: Date = Date(),
                val name: String = "",
                val ingredients: List<Ingredient> = emptyList())