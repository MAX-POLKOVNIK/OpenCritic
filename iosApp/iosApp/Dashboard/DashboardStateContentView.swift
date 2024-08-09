//
//  DashboardStateContent.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardStateContentView: View {
    @Environment(\.horizontalSizeClass) 
    private var horizontalSizeClass: UserInterfaceSizeClass?
    
    let state: DashboardContent
    
    var body: some View {
        ScrollView(.vertical) {
            LazyVStack(alignment: .leading) {
                DashboardTitleListItemView(item: state.popularGamesTitle)
                    .padding(.top)
                DashboardPosterGamesHorizontalListItemView(item: state.popularGames)
                Spacer()
                    .frame(height: 16)
                
                DashboardTitleListItemView(item: state.dealsTitle)
                DashboardDealsHorizontalListItemView(item: state.deals)
                Spacer()
                    .frame(height: 16)
                
                if horizontalSizeClass == .compact {
                    DashboardSublistListitemView(item: state.recentlyReleased)
                    Spacer()
                        .frame(height: 24)
                    DashboardSublistListitemView(item: state.upcomingReleases)
                    Spacer()
                        .frame(height: 24)
                    
                    DashboardSublistListitemView(item: state.reviewedToday)
                    Spacer()
                        .frame(height: 24)
                } else {
                    Grid {
                        GridRow {
                            DashboardSublistListitemView(item: state.recentlyReleased)
                            DashboardSublistListitemView(item: state.upcomingReleases)
                        }
                        GridRow {
                            DashboardSublistListitemView(item: state.reviewedToday)
                        }
                    }
                    
                    Spacer()
                        .frame(height: 24)
                }
                
                DashboardTitleListItemView(item: state.hallOfFameTitle)
                DashboardPosterGamesHorizontalListItemView(item: state.hallOfFame)
                Spacer()
                    .frame(height: 16)
                
                DashboardMightyManListItemView(item: state.whoIsMightyMan)
                Spacer()
                    .frame(height: 16)
                
                DashboardTitleListItemView(item: state.switchTitle)
                DashboardPosterGamesHorizontalListItemView(item: state.switchGames)
                Spacer()
                    .frame(height: 16)
                
                DashboardTitleListItemView(item: state.xboxTitle)
                DashboardPosterGamesHorizontalListItemView(item: state.xboxGames)
                Spacer()
                    .frame(height: 16)
                
                DashboardTitleListItemView(item: state.playstationTitle)
                DashboardPosterGamesHorizontalListItemView(item: state.playstationGames)
                Spacer()
                    .frame(height: 16)
            }
        }
    }
}

#Preview {
    DashboardStateContentView(
        state: DashboardContentKt.DashboardContent_PreviewData()
    )
}
