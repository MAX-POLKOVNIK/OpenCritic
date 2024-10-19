//
//  MainStateView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine
import Navigation

struct MainStateView: View {
    let state: MainContent
    
    @State private var activeTab: TabType
    
    init(state: MainContent) {
        self.state = state
        self.activeTab = state.currentTab.id
    }
    
    var body: some View {
        TabView(selection: $activeTab) {
            ForEach(state.tabs, id: \.self) { tab in
                RouterView { createView(by: tab.screenCreator!, with: tab.args!) }
                    .tabItem {
                        Label(tab.name, iconRes: tab.imageResource)
                    }
                    .tag(tab.id)
            }
        }
    }
}
