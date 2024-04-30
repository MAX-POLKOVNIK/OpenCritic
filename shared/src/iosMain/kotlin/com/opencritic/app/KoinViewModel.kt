package com.opencritic.app

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClassOf
import kotlinx.cinterop.ObjCObject
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.mp.KoinPlatform.getKoin
import org.koin.mp.KoinPlatformTools
import kotlin.reflect.KClass

@OptIn(BetaInteropApi::class)
fun <T : ObjCObject> ObjCClassOf<T>.koinViewModel(): T {
    val kClass: KClass<*>? = getOriginalKotlinClass(this)

    requireNotNull(kClass) {
        "Kotlin type of $this not found. This type can't be used as ViewModel"
    }


    return KoinPlatformTools.defaultContext().get().get(kClass)
//    return getKoin().get(kClass)
}
