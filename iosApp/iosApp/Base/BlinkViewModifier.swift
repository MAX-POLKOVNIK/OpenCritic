//
//  BlinkViewModifier.swift
//  iosApp
//
//  Created by Max Polkovnik on 11/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct BlinkViewModifier: ViewModifier {
    
    let duration: Double
    @State private var blinking: Bool = false
    
    func body(content: Content) -> some View {
        content
            .opacity(blinking ? 1 : 0.1)
            .animation(.easeOut(duration: duration).repeatForever(), value: blinking)
            .onAppear {
                withAnimation {
                    blinking = true
                }
            }
    }
}

extension View {
    func blinking(duration: Double = 0.75) -> some View {
        modifier(BlinkViewModifier(duration: duration))
    }
}
