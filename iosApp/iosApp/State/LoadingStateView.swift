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
        VStack {
            ProgressView()
                .padding(.vertical)
            Text(state.text)
        }
    }
}

private class P : BaseLoadingState {
    override init(text: String) {
        super.init(text: text)
    }
}

#Preview {
    LoadingStateView(
        state: P(text: "Loading...")
    )
}
