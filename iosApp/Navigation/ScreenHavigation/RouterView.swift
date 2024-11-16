//
//  RouterView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

public struct RouterView<Content: View>: View {
    @StateObject var router: IosRouter = IosRouter()
    
    private let content: Content
    
    @State private var isShow: Bool = true
    
    public init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content()
    }
    
    public var body: some View {
        NavigationStack(path: $router.path) {
            content
                .navigationDestination(for: Destination.self) { route in
                    let _ = withAnimation {
                        isShow = $router.path.isEmpty
                    }
                    
                    router.view(for: route)
                }
                .toolbar(isShow ? .visible : .hidden, for: .tabBar)
                .onAppear {
                    withAnimation {
                        isShow = true
                    }
                }
                .onDisappear {
                    withAnimation {
                        isShow = $router.path.isEmpty
                    }
                }
        }
        .environmentObject(router)
        .environmentObject(router.viewModelStore)
    }
}

