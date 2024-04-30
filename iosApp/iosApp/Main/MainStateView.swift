//
//  MainStateView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainStateView: View {
    let state: MainState
    
    var body: some View {
        TabView {
            ForEach(state.tabs, id: \.self) { tab in
                if tab.id == TabType.main {
                    DashboardScreenView()
                        .tabItem {
                            Label(tab.name, systemImage: tab.imageResource)
                        }
                }
                if tab.id == TabType.search {
                    ContentView()
                        .tabItem {
                            Label(tab.name, systemImage: tab.imageResource)
                        }
                }
                if tab.id == TabType.browse {
                    ContentView()
                        .tabItem {
                            Label(tab.name, systemImage: tab.imageResource)
                        }
                }
                if tab.id == TabType.calendar {
                    ContentView()
                        .tabItem {
                            Label(tab.name, systemImage: tab.imageResource)
                        }
                }
                if tab.id == TabType.yourlists {
                    ContentView()
                        .tabItem {
                            Label(tab.name, systemImage: tab.imageResource)
                        }
                }
            }
        }
    }
}

#Preview {
    MainStateView(
        state: MainState(
            tabs: [
                Tab(id: TabType.main, name: "Test", imageResource: "star"),
                Tab(id: TabType.search, name: "Test2", imageResource: "star")
            ]
        )
    )
}
