//
//  GameDetailsStateContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameDetailsContentView: View {
    @Environment(\.horizontalSizeClass)
    private var horizontalSizeClass: UserInterfaceSizeClass?
    
    let state: GameDetailsContent
    
    var body: some View {
        ScrollView(.vertical) {
            LazyVStack(alignment: .leading) {
                
                if horizontalSizeClass == .compact {
                    GameDetailsCompactView(state: state)
                } else {
                    GameDetailsRegularView(state: state)
                }
                
                if state.isMediaVisible {
                    Spacer()
                        .frame(height: 32)
                    
                    Text(state.mediaText)
                        .font(.title)
                        .padding(.horizontal)
                    
                    if horizontalSizeClass == .compact {
                        ForEach(state.media, id: \.self) { m in
                            switch m {
                            case let trailer as TrailerItem:
                                TrailerItemView(item: trailer)
                                    .padding(.horizontal)
                            case let screenshot as ScreenshotItem:
                                ScreenshotItemView(item: screenshot)
                                    .padding(.horizontal)
                            default: fatalError()
                            }
                        }
                    } else {
                        ScrollView(.horizontal, showsIndicators: false) {
                            LazyHStack(spacing: 10) {
                                ForEach(state.media, id: \.self) { m in
                                    switch m {
                                    case let trailer as TrailerItem:
                                        TrailerItemView(item: trailer)
                                    case let screenshot as ScreenshotItem:
                                        ScreenshotItemView(item: screenshot)
                                    default: fatalError()
                                    }
                                }
                            }
                            .frame(height: 200)
                            .padding(.horizontal)
                        }
                    }
                    
                    HStack {
                        Spacer()
                        Button(state.viewAllMedia) { state.onViewAllMediaClick() }
                            .padding()
                    }
                }
                
                if state.isTrailersVisible {
                    Spacer()
                        .frame(height: 32)
                    
                    Text(state.trailersText)
                        .font(.title)
                        .padding(.horizontal)
                    
                    if horizontalSizeClass == .compact {
                        ForEach(state.trailers, id: \.self) { trailer in
                            TrailerItemView(item: trailer)
                                .padding(.horizontal)
                        }
                    } else {
                        ScrollView(.horizontal, showsIndicators: false) {
                            LazyHStack(spacing: 10) {
                                ForEach(state.trailers, id: \.self) { trailer in
                                    TrailerItemView(item: trailer)
                                }
                            }
                            .frame(height: 250)
                            .padding(.horizontal)
                        }
                    }
                    
                    HStack {
                        Spacer()
                        Button(state.viewAllTrailers) { state.onViewAllTrailersClick() }
                            .padding()
                    }
                }
                
                if state.isScreenshotsVisible {
                    Spacer()
                        .frame(height: 32)
                    
                    Text(state.screenshotsText)
                        .font(.title)
                        .padding(.horizontal)
                    
                    if horizontalSizeClass == .compact {
                        ForEach(state.screenshots, id: \.self) { screenshot in
                            ScreenshotItemView(item: screenshot)
                                .padding(.horizontal)
                        }
                    } else {
                        ScrollView(.horizontal, showsIndicators: false) {
                            LazyHStack(spacing: 10) {
                                ForEach(state.screenshots, id: \.self) { screenshot in
                                    ScreenshotItemView(item: screenshot)
                                }
                            }
                            .frame(height: 200)
                            .padding(.horizontal)
                        }
                    }
                    
                    HStack {
                        Spacer()
                        Button(state.viewAllScreenshots) { state.onViewAllScreenshotsClick() }
                            .padding()
                    }
                }
                
                if state.isReviewsVisible {
                    Text(state.reviewTitleText)
                        .font(.title)
                        .padding(.horizontal)
                    
                    if horizontalSizeClass == .compact {
                        ForEach(state.reviews, id: \.self) { review in
                            CardReviewItemView(item: review)
                                .padding(.horizontal)
                        }
                    } else {
                        LazyVGrid(
                            columns: [
                                .init(.flexible(), alignment: .top),
                                .init(.flexible(), alignment: .top)
                            ],
                            spacing: 20
                        ) {
                            ForEach(state.reviews, id: \.self) { item in
                                CardReviewItemView(item: item)
                            }
                        }
                        .padding()
                    }
                    
                    if state.isViewAllVisible {
                        HStack {
                            Spacer()
                            Button(state.viewAllText) { state.onViewAllReviewsClick() }
                                .padding()
                        }
                    }
                }
            }
        }
        .toolbar {
            if state.isActionVisible {
                Button(
                    action: state.onAction,
                    label: { Image(iconRes: state.actionIconResource) }
                )
            }
        }
        .onAppear { state.onRefresh() }
    }
}

#Preview {
    GameDetailsContentView(
        state: GameDetailsContentKt.GameDetailsContent_PreviewData()
    )
}
