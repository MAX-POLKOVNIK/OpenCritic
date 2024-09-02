//
//  ColorExtensions.swift
//  iosApp
//
//  Created by Max Polkovnik on 03/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit
import SwiftUI
import shared

public extension UIColor {
    func toColor() -> SwiftUI.Color {
        Color(self)
    }
}

public extension shared.Color {
    var color: SwiftUI.Color {
        self.toUIColor().toColor()
    }
}
