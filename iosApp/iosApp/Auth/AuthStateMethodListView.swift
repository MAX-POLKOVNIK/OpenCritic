//
//  AuthStateMethodListView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AuthStateMethodListView: View {
    let state: AuthStateMethodList
    
    var body: some View {
        VStack(
            alignment: .leading
        ) {
            ForEach(state.items, id: \.self) { item in
                HStack(alignment: .center) {
                    Image(item.imageResource)
                        .resizable()
                        .frame(width: 56, height: 56)
                    
                    Text(item.text)
                    
                    Spacer()
                }
                .padding(.horizontal)
                .contentShape(Rectangle())
                .onTapGesture { item.onClick() }
            }
        }
        .navigationBarTitle(state.titleText, displayMode: .large)
        
        Spacer()
    }
}

#Preview {
    AuthStateMethodListView(
        state: AuthStateKt.AuthStateMethodList_PreviewData()
    )
}
