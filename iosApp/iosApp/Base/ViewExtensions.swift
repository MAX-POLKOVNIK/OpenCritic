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

extension View {
    func card(radius: CGFloat = 8) -> some View {
        if UITraitCollection.current.userInterfaceStyle == .dark {
            AnyView(
                self.clipShape(.rect(cornerRadius: radius))
                    .background(
                        RoundedRectangle(cornerRadius: radius)
                            .fill(Color(red: 30 / 255, green: 30 / 255, blue: 30 / 255))
                    )
            )
        } else {
            AnyView(
                self.clipShape(.rect(cornerRadius: radius))
                    .background(
                        RoundedRectangle(cornerRadius: radius)
                            .fill(UIColor.systemBackground.toColor())
                            .shadow(color: .gray, radius: radius / 4, x: 0, y: radius / 4)
                    )
            )
        }
    }
    
    func navigationTitle(_ textSource: TextSource) -> some View {
        navigationBarTitle(textSource.text())
    }
}
