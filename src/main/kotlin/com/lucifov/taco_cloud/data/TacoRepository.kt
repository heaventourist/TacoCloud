package com.lucifov.taco_cloud.data

import com.lucifov.taco_cloud.models.Taco
import org.springframework.data.repository.CrudRepository

interface TacoRepository: CrudRepository<Taco, Long>