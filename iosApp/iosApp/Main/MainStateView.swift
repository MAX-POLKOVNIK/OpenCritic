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

// temporary workaround
private let dashboardViewModel: DashboardViewModel = koinViewModel(DashboardViewModel.self)
private let searchViewModel: SearchViewModel = koinViewModel(SearchViewModel.self)
private let gameBrowserViewModel: GameBrowserViewModel = koinViewModel(GameBrowserViewModel.self)
private let yourGameListViewModel: YourGameListViewModel = koinViewModel(YourGameListViewModel.self)

struct MainStateView: View {
    let state: MainState
    
    @EnvironmentObject var router: IosRouter
    @State private var activeTab: TabType = TabType.main
    
    var body: some View {
        dashboardViewModel.setRouter(router: router)
        searchViewModel.setRouter(router: router)
        gameBrowserViewModel.setRouter(router: router)
        yourGameListViewModel.setRouter(router: router)
        
        return VStack {
            TabView(selection: $activeTab) {
                ForEach(state.tabs, id: \.self) { tab in
                    if tab.id == TabType.main {
                        DashboardScreenView(viewModel: dashboardViewModel)
                            .tabItem {
                                Label(tab.name, systemImage: tab.imageResource)
                            }
                            .tag(tab.id)
                            .navigationTitle(tab.name)
                    }
                    if tab.id == TabType.search {
                        SearchScreenView(viewModel: searchViewModel)
                            .tabItem {
                                Label(tab.name, systemImage: tab.imageResource)
                            }
                            .tag(tab.id)
                            .navigationTitle(tab.name)
                    }
                    if tab.id == TabType.browse {
                        GameBrowserScreenView(viewModel: gameBrowserViewModel)
                            .tabItem {
                                Label(tab.name, systemImage: tab.imageResource)
                            }
                            .tag(tab.id)
                            .navigationTitle(tab.name)
                    }
                    if tab.id == TabType.yourlists {
                        YourGameListScreenView(viewModel: yourGameListViewModel)
                            .tabItem {
                                Label(tab.name, systemImage: tab.imageResource)
                            }
                            .tag(tab.id)
                            .navigationTitle(tab.name)
                    }
                }
            }
        }
        .navigationBarTitle(
            state.tabs.first { $0.id == activeTab }?.name ?? "",
            displayMode: .inline
        )
        .overlay(alignment: .top) {
            Color.clear // Or any view or color
                .background(.regularMaterial) // I put clear here because I prefer to put a blur in this case. This modifier and the material it contains are optional.
                .ignoresSafeArea(edges: .top)
                .frame(height: 0) // This will constrain the overlay to only go above the top safe area and not under.
        }
    }
}
