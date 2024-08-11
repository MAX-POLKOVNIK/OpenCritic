//
//  LoadingStateView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoadingStateView: View {
    let state: BaseLoadingState
    
    var body: some View {
        GeometryReader { proxy in
            ScrollView {
                VStack {
                    Spacer()
                    
                    Image(uiImage: MR.images.shared.open_critic_logo.toUIImage()!)
                        .resizable()
                        .colorMultiply(.primary)
                        .aspectRatio(1, contentMode: .fit)
                        .frame(maxWidth: 48, maxHeight: 48)
                        .padding()
                    
                    Text(state.text)
                    
                    Spacer()
                }
                .blinking(duration: 3)
                .frame(minWidth: proxy.size.width, minHeight: proxy.size.height)
            }
        }
        .edgesIgnoringSafeArea(.all)
    }
}

private class P : BaseLoadingState {
    override init(text: String) {
        super.init(text: text)
    }
}

#Preview {
    LoadingStateView(
        state: P(text: MR.strings().str_loading.desc().localized())
    )
}
