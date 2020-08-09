package com.lucifov.taco_cloud.data

import com.lucifov.taco_cloud.models.Ingredient
import org.springframework.data.repository.CrudRepository

interface IngredientRepository: CrudRepository<Ingredient, String>