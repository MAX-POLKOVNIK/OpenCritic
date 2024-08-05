//
//  ErrorStateView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ErrorStateView: View {
    let state: BaseErrorState
    
    var body: some View {
        VStack {
            Image(iconRes: Icons.shared.warning)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .foregroundColor(.red)
                .frame(width: 56, height: 56)
            
            
            Text(state.message)
                .padding(.horizontal)
            
            Button(state.actionText, action: state.action)
                .padding()
        }
    }
}

#Preview {
    ErrorStateView(
        state: BaseErrorState(
            message: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.".asTextSource(),
            actionText: "Retry".asTextSource(),
            action: {}
        )
    )
}
