//
//  IntExtensions.swift
//  BuildSharedFramework
//
//  Created by Max Polkovnik on 17/11/2024.
//

import shared

public extension Int {
    var ktInt32: KotlinInt {
        KotlinInt(value: Int32(self))
    }
}
