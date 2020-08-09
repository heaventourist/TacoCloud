package com.lucifov.taco_cloud.data

import com.lucifov.taco_cloud.models.Ingredient

interface IngredientRepository {
    fun findAll(): Iterable<Ingredient>
    fun findOne(id: String): Ingredient
    fun save(ingredient: Ingredient): Ingredient
}