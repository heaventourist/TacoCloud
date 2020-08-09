package com.lucifov.taco_cloud.models

import javax.persistence.*

@Entity
data class Ingredient(@Id @Column(name = "id") var id: String="",
                      @Column(name="name") var name: String="",
                      @Enumerated(EnumType.STRING) @Column(name = "type") var type: Type = Type.NONE) {
    enum class Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE, NONE
    }
}