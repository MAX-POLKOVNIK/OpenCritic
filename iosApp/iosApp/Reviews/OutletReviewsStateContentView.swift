//
//  OutletReviewsStateContent.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct OutletReviewsStateContentView: View {
    let state: OutletReviewsStateContent
    
    @State private var selection = ReviewSortItem(key: ReviewSorting.default_, name: "Def")
    
    init(state: OutletReviewsStateContent) {
        self.state = state
        
        selection = state.sortText
    }
    
    var body: some View {
        List {
            VStack(alignment: .center) {
                CachedAsyncImage(
                    url: URL(string: state.outletImageUrl),
                    urlCache: .imageCache
                ) { image in
                    image.resizable()
                } placeholder: {
                    Color.gray
                }
                .frame(width: 128, height: 128)
                .clipShape(Circle())
                
                Text(state.outletNameText)
                    .font(.title)
                
                if state.isHomepageVisible {
                    Button(state.homepageText) { state.onHomepageClick() }
                        .buttonStyle(BorderlessButtonStyle())
                }
                
                Spacer()
                
                ForEach(state.infoItems, id: \.self) { item in
                    IconTextItemView(item: item)
                }
            }
            .padding()
            .listRowSeparator(.hidden)
            .card()
            
            Picker(state.sortTitleText, selection: $selection) {
                ForEach(state.availableSorts, id: \.self) {
                    Text($0.name)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(selection)) { _ in
                state.onSelectedSort(selection)
            }
            
            ForEach(state.reviewItems, id: \.self) { item in
                ReviewListItemView(item: item)
                    .buttonStyle(BorderlessButtonStyle())
            }
            
            if state.isLoadingItemVisible {
                DashboardStateLoadingView(state: DashboardStateLoading.shared)
                    .onAppear {
                        state.onLoadMore()
                    }
            }
        }
        .listStyle(.plain)
        .navigationBarTitle(state.titleText, displayMode: .large)
    }
}

#Preview {
    OutletReviewsStateContentView(
        state: OutletReviewsStateKt.OutletReviewsStateContent_PreviewData(
            imageResourceProvider: IosImagesResourceProvider()
        )
    )
}
