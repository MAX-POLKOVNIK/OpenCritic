//
//  NavigationBarTitleExtensions.swift
//  iosApp
//
//  Created by Max Polkovnik on 31/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

public extension View {
    func navigationBarTitle(_ title: (any TextSource)?, displayMode: NavigationBarItem.TitleDisplayMode) -> some View {
        self.navigationTitle(title?.text() ?? "")
            .navigationBarTitleDisplayMode(displayMode)
    }
    
    func navigationTitle(_ textSource: TextSource) -> some View {
        navigationBarTitle(textSource.text())
    }
}
