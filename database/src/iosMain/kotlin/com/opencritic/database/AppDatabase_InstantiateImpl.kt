package com.opencritic.database

import kotlin.reflect.KClass

internal fun <T : Any> KClass<T>.instantiateImpl(): T =
    error("${this.simpleName} : Room database was not generated. Add ksp for all your targets")