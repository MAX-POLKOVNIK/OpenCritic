//
//  ButtonExtensions.swift
//  iosApp
//
//  Created by Max Polkovnik on 31/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

extension Button where Label == Text {
    init(_ textSource: TextSource, action: @escaping () -> Void) {
        self.init(textSource.text(), action: action)
    }
}
