package com.lucifov.taco_cloud.data

import com.lucifov.taco_cloud.models.Ingredient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class JdbcIngredientRepository(@Autowired private val jdbc: JdbcTemplate): IngredientRepository {
    private val mapRowToIngredient: RowMapper<Ingredient> = RowMapper{
        rs: ResultSet, rowNum: Int ->  Ingredient(rs.getString("id"),
            rs.getString("name"),
            Ingredient.Type.valueOf(rs.getString("type")))
    }

    override fun findAll(): Iterable<Ingredient> {
        return jdbc.query("select id, name, type from Ingredient", mapRowToIngredient)
    }

    override fun findOne(id: String): Ingredient? {
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?",
                mapRowToIngredient, id)
    }

    override fun save(ingredient: Ingredient): Ingredient {
        jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
        ingredient.id, ingredient.name, ingredient.type)
        return ingredient
    }
}