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
        VStack {
            TabView {
                ForEach(state.tabs, id: \.self) { tab in
                    if tab.id == TabType.main {
                        DashboardScreenView()
                            .tabItem {
                                Label(tab.name, systemImage: tab.imageResource)
                            }
                            .navigationBarTitle(tab.name, displayMode: .large)
                    }
                    if tab.id == TabType.search {
                        SearchScreenView()
                            .tabItem {
                                Label(tab.name, systemImage: tab.imageResource)
                            }
                            .navigationBarTitle(tab.name, displayMode: .large)
                    }
                    if tab.id == TabType.browse {
                        ContentView()
                            .tabItem {
                                Label(tab.name, systemImage: tab.imageResource)
                            }
                            .navigationBarTitle(tab.name, displayMode: .large)
                    }
                    if tab.id == TabType.calendar {
                        ContentView()
                            .tabItem {
                                Label(tab.name, systemImage: tab.imageResource)
                            }
                            .navigationBarTitle(tab.name, displayMode: .large)
                    }
                    if tab.id == TabType.yourlists {
                        ContentView()
                            .tabItem {
                                Label(tab.name, systemImage: tab.imageResource)
                            }
                            .navigationBarTitle(tab.name, displayMode: .large)
                    }
                }
            }
        }
        
        .navigationBarTitle("OpenCritic", displayMode: .inline)
        .overlay(alignment: .top) {
            Color.clear // Or any view or color
                .background(.regularMaterial) // I put clear here because I prefer to put a blur in this case. This modifier and the material it contains are optional.
                .ignoresSafeArea(edges: .top)
                .frame(height: 0) // This will constrain the overlay to only go above the top safe area and not under.
        }
    }
}
