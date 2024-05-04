//
//  ViewExtensions.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension View {
    func card(radius: CGFloat = 8) -> some View {
        self
            .clipShape(.rect(cornerRadius: radius))
            .background(
                RoundedRectangle(cornerRadius: radius)
                    .fill(UIColor.systemBackground.toColor())
                    .shadow(color: .gray, radius: radius / 4, x: 0, y: radius / 4)
            )
    }
}
