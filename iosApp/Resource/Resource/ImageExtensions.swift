//
//  ImageExtensions.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

public extension Image {
    init(iconRes: IconResource) {
        if let iconResourceSfSymbol = iconRes as? IconResourceSfSymbol {
            self.init(systemName: iconResourceSfSymbol.name)
        } else {
            fatalError("Can't instantiate image with \(iconRes)")
        }
    }
    
    init(_ sharedImageResource: SharedImageResource) {
        if let image = sharedImageResource.imageRes.toUIImage() {
            self.init(uiImage: image)
        } else {
            fatalError("Can't instantiate image with \(sharedImageResource)")
        }
    }
}
