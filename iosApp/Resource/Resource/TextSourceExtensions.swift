//
//  TextSourceExtensions.swift
//  iosApp
//
//  Created by Max Polkovnik on 31/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

public extension TextSource {
    func text() -> String {
        TextSource_iosKt.text(self)
    }
}

public extension String {
    func asTextSource() -> TextSource {
        StringTextSource(string: self)
    }
}
