//
//  StringHtmlToMarkdown.swift
//  News
//
//  Created by Max Polkovnik on 16/11/2024.
//

import UIKit
import SwiftUI

extension String {
    func htmlToAttributedString() -> AttributedString {
        let data = Data(utf8)
        
        let nsAttributedString = try! NSAttributedString(data: data, options: [.documentType: NSAttributedString.DocumentType.html], documentAttributes: nil)
        let mutableAttributedString = NSMutableAttributedString(attributedString: nsAttributedString)
        mutableAttributedString.setFontFace(font: .preferredFont(from: .body), color: .label)
        
        return AttributedString(mutableAttributedString)
    }
}

extension UIFont {
    class func preferredFont(from font: Font) -> UIFont {
        let style: UIFont.TextStyle =
            switch font {
            case .largeTitle:   .largeTitle
            case .title:        .title1
            case .title2:       .title2
            case .title3:       .title3
            case .headline:     .headline
            case .subheadline:  .subheadline
            case .callout:      .callout
            case .caption:      .caption1
            case .caption2:     .caption2
            case .footnote:     .footnote
            default: /*.body */ .body
            }
        return  UIFont.preferredFont(forTextStyle: style)
    }
 }

extension NSMutableAttributedString {
    func setFontFace(font: UIFont, color: UIColor? = nil) {
        beginEditing()
        
        self.enumerateAttribute(
            .font,
            in: NSRange(location: 0, length: self.length)
        ) { (value, range, stop) in
            if let f = value as? UIFont,
                let newFontDescriptor = f.fontDescriptor
                    .withFamily(font.familyName)
                    .withSymbolicTraits(f.fontDescriptor.symbolicTraits) {

                    let newFont = UIFont(
                        descriptor: newFontDescriptor,
                        size: font.pointSize
                    )
                    removeAttribute(.font, range: range)
                    addAttribute(.font, value: newFont, range: range)
                
                    if let color = color {
                        removeAttribute(
                            .foregroundColor,
                            range: range
                        )
                        addAttribute(
                            .foregroundColor,
                            value: color,
                            range: range
                        )
                    }
            }
        }
        
        endEditing()
    }
}
