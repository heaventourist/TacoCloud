package com.lucifov.taco_cloud.data

import com.lucifov.taco_cloud.models.Ingredient
import com.lucifov.taco_cloud.models.Taco
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Repository
class JdbcTacoRepository(@Autowired val jdbc: JdbcTemplate) : TacoRepository {
    override fun save(taco: Taco): Taco {
        val tacoId = saveTacoInfo(taco)
        taco.ingredients.forEach { ingredient ->
            saveIngredientToTaco(ingredient, tacoId)
        }
        return taco.copy(id=tacoId)
    }

    private fun saveTacoInfo(taco: Taco): Long {
        val psc: PreparedStatementCreator = PreparedStatementCreator { con ->
            con.prepareStatement("insert into Taco (name, createdAt) values (?, ?)").also {
                it.setNString(1, taco.name)
                it.setTimestamp(2, Timestamp(taco.createdAt.time))
            }
        }
        val keyHolder = GeneratedKeyHolder()
        jdbc.update(psc, keyHolder)
        return keyHolder.key?.toLong()?:throw Exception("save taco info returns null id")
    }

    private fun saveIngredientToTaco(ingredient: Ingredient, tacoId: Long) {
        jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
                tacoId, ingredient.id)
    }
}