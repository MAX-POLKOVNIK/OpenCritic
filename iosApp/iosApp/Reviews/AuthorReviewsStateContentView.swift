//
//  AuthorReviewsStateContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct AuthorReviewsStateContentView: View {
    let state: AuthorReviewsStateContent
    
    @State private var selection = ReviewSortItem(key: ReviewSorting.default_, name: "Def")
    
    init(state: AuthorReviewsStateContent) {
        self.state = state
        
        selection = state.sortText
    }
    
    var body: some View {
        List {
            VStack(alignment: .center) {
                CachedAsyncImage(
                    url: URL(string: state.imageUrl),
                    urlCache: .imageCache
                ) { image in
                    image.resizable()
                } placeholder: {
                    Image(systemName: "person.crop.circle.fill")
                        .resizable()
                }
                .frame(width: 128, height: 128)
                .clipShape(Circle())
                
                Text(state.nameText)
                    .font(.title)
                    .bold()
                
                Spacer()
                
                ForEach(state.personalInfoItems, id: \.self) { item in
                    IconTextItemView(item: item)
                }
                
                Spacer()
                
                if state.isFavoritesGamesVisible {
                    Divider()
                    
                    HStack {
                        Image(iconRes: Icons.shared.heart)
                            .foregroundColor(.red)
                        
                        Text(state.favoritesGamesTitleText)
                        Spacer()
                    }
                    
                    VStack(alignment: .leading) {
                        ForEach(state.favoritesGames, id: \.self) { item in
                            HStack {
                                Text(item)
                                    .multilineTextAlignment(.leading)
                                
                                Spacer()
                            }
                            .padding(.horizontal, 28)
                        }
                    }
        
                    Divider()
                }
                
                ForEach(state.countersInfoItems, id: \.self) { item in
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
                LoadingItemView(item: state.loadingItem)
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
    AuthorReviewsStateContentView(
        state: AuthorReviewsStateKt.AuthorReviewsStateContent_PreviewData()
    )
}
