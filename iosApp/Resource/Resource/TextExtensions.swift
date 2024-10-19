//
//  TextExtensions.swift
//  iosApp
//
//  Created by Max Polkovnik on 31/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

public extension Text {
    init(_ textSource: TextSource) {
        self.init(textSource.text())
    }
}
