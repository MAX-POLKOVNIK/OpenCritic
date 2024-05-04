//
//  IosDateFormatter.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class IosDateFormatter : shared.DateFormatter {
    func format(date: Kotlinx_datetimeLocalDate) -> String {
        if let date = Calendar.current.date(from: DateComponents(year: Int(date.year), month: Int(date.monthNumber), day: Int(date.dayOfMonth))) {
            let formatter = DateFormatter()
            formatter.dateFormat = "MMM dd, yyyy"
            return formatter.string(from: date)
        }
        
        return ""
    }
    
    func formatShort(date: Kotlinx_datetimeLocalDate) -> String {
        if let date = Calendar.current.date(from: DateComponents(year: Int(date.year), month: Int(date.monthNumber), day: Int(date.dayOfMonth))) {
            let formatter = DateFormatter()
            formatter.dateFormat = "MMM dd"
            return formatter.string(from: date)
        }
        
        return ""
    }
    
    func formatFull(date: Kotlinx_datetimeLocalDate) -> String {
        if let date = Calendar.current.date(from: DateComponents(year: Int(date.year), month: Int(date.monthNumber), day: Int(date.dayOfMonth))) {
            let formatter = DateFormatter()
            formatter.dateFormat = "MMMM dd, yyyy"
            return formatter.string(from: date)
        }
        
        return ""
    }
}
