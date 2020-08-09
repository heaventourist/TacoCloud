package com.lucifov.taco_cloud.models

import java.util.*
import javax.persistence.*

@Entity
data class Taco(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") var id: Long = -1,
                @Column(name = "createdAt")var createdAt: Date = Date(),
                @Column(name = "name") var name: String = "",
                @ManyToMany(targetEntity = Ingredient::class)
                @JoinTable(name = "Taco_Ingredients", joinColumns = [JoinColumn(name = "tacoId")], inverseJoinColumns = [JoinColumn(name="ingredientId")])
                var ingredients: MutableList<Ingredient> = mutableListOf()){
    @PrePersist
    fun prepersist(){
        createdAt = Date()
    }
}