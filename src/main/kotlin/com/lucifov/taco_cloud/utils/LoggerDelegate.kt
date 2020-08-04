package com.lucifov.taco_cloud.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.companionObject

class LoggerDelegate<R: Any>: ReadOnlyProperty<R, Logger> {
    override operator fun getValue(thisRef: R, property: KProperty<*>): Logger{
        return LoggerFactory.getLogger(getClassForLogging(thisRef.javaClass))
    }

    private fun <T: Any> getClassForLogging(javaClass: Class<T>): Class<*>{
        return javaClass.enclosingClass?.takeIf {
            it.kotlin.companionObject?.java == javaClass
        }?:javaClass
    }
}