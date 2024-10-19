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
private let articleListViewModel: ArticleListViewModel = koinViewModel(ArticleListViewModel.self)

struct MainStateView: View {
    let state: MainContent
    private let router = IosRouter.shared
    
    @State private var activeTab: TabType
    
    init(state: MainContent) {
        self.state = state
        self.activeTab = state.currentTab.id
    }
    
    var body: some View {
        dashboardViewModel.setRouter(router: router)
        searchViewModel.setRouter(router: router)
        gameBrowserViewModel.setRouter(router: router)
        yourGameListViewModel.setRouter(router: router)
        articleListViewModel.setRouter(router: router)
        
        return TabView(selection: $activeTab) {
            ForEach(state.tabs, id: \.self) { tab in
                if tab.id == TabType.dashboard {
                    RouterView {
                        DashboardScreenView(viewModel: dashboardViewModel)
                    }
                        .tabItem {
                            Label(tab.name, iconRes: tab.imageResource)
                        }
                        .tag(tab.id)
                }
                if tab.id == TabType.search {
                    RouterView {
                        SearchScreenView(viewModel: searchViewModel)
                    }
                        .tabItem {
                            Label(tab.name, iconRes: tab.imageResource)
                        }
                        .tag(tab.id)
                }
                if tab.id == TabType.browse {
                    RouterView {
                        GameBrowserScreenView(viewModel: gameBrowserViewModel)
                    }
                        .tabItem {
                            Label(tab.name, iconRes: tab.imageResource)
                        }
                        .tag(tab.id)
                }
                if tab.id == TabType.yourlists {
                    RouterView {
                        YourGameListScreenView(viewModel: yourGameListViewModel)
                    }
                        .tabItem {
                            Label(tab.name, iconRes: tab.imageResource)
                        }
                        .tag(tab.id)
                }
                if tab.id == TabType.news {
                    RouterView {
                        ArticleListScreenView(viewModel: articleListViewModel)
                    }
                        .tabItem {
                            Label(tab.name, iconRes: tab.imageResource)
                        }
                        .tag(tab.id)
                }
            }
        }
    }
}
