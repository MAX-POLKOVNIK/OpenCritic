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

public extension Label where Title == Text, Icon == Image {
    init(_ title: TextSource, iconRes: IconResource) {
        if let iconResourceSfSymbol = iconRes as? IconResourceSfSymbol {
            self.init(title.text(), systemImage: iconResourceSfSymbol.name)
        } else {
            fatalError("Can't instantiate label with \(iconRes)")
        }
    }
}
