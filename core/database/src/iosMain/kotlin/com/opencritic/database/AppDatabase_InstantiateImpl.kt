package com.opencritic.database

import kotlin.reflect.KClass

// Fake extension to suppress IDE error
// Real method generated in separate sourceSet for each platform
internal fun <T : Any> KClass<T>.instantiateImpl(): T =
    error("${this.simpleName} : Room database was not generated. Add ksp for all your targets")