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

extension View {
    public func navigationBarTitle(_ title: TextSource, displayMode: NavigationBarItem.TitleDisplayMode) -> some View {
        return self.navigationTitle(title.text())
            .navigationBarTitleDisplayMode(displayMode)
    }
}
