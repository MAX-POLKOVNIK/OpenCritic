//
//  HtmlTextSourceExtensions.swift
//  Resource
//
//  Created by Max Polkovnik on 18/11/2024.
//

import shared
import SwiftUI

public extension HtmlTextSource {
    func text(font: Font = .body, color: UIColor = .label) -> AttributedString {
        htmlString.htmlToAttributedString(font: font, color: color)
    }
}
