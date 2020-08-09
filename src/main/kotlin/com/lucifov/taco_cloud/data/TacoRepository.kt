package com.lucifov.taco_cloud.data

import com.lucifov.taco_cloud.models.Taco

interface TacoRepository {
    fun save(taco: Taco): Taco
}