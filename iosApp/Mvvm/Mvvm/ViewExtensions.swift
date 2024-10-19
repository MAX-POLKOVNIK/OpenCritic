//
//  ViewExtensions.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import UIKit
import shared

public extension View {
    func card(radius: CGFloat = 8) -> some View {
        let color = UITraitCollection.current.userInterfaceStyle == .dark
            ? Color(red: 30 / 255, green: 30 / 255, blue: 30 / 255)
            : Color(red: 245 / 255, green: 245 / 255, blue: 245 / 255)
        
        return AnyView(
            self.clipShape(.rect(cornerRadius: radius))
                .background(
                    RoundedRectangle(cornerRadius: radius)
                        .fill(color)
                )
        )
    }
}
