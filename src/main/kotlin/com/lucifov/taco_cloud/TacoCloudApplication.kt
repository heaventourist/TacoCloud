package com.lucifov.taco_cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TacoCloudApplication

fun main(args: Array<String>) {
	runApplication<TacoCloudApplication>(*args)
}
