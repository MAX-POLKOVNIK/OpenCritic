//
//  LabelExtensions.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension Label where Title == Text, Icon == Image {
    init<S>(_ title: S, iconRes: IconResource) where S : StringProtocol {
        if let iconResourceSfSymbol = iconRes as? IconResourceSfSymbol {
            self.init(title, systemImage: iconResourceSfSymbol.name)
        } else {
            fatalError("Can't instantiate label with \(iconRes)")
        }
    }
}
