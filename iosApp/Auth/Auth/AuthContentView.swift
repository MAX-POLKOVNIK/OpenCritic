//
//  AuthContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct AuthContentView: View {
    private let content: AuthContent
    
    @State private var token: String
    
    init(content: AuthContent) {
        self.content = content
        self.token = content.token
    }
    
    var body: some View {
        VStack(alignment: .center) {
            Text(content.descriptionText)
                .padding(.horizontal)
            
            TextField(content.tokenHint.text(), text: $token, axis: .vertical)
                .textFieldStyle(.roundedBorder)
                .padding()
                .onReceive(Just(token)) { _ in
                    content.onTokenChanged(token)
                }
            
            Button(content.authButtonText) {
                content.onAuthButtonClicked()
            }
            
            Button(content.useOfflineListsText) {
                content.onUseOfflineListsClick()
            }
            .padding(.vertical)
            
            Spacer()
        }
    }
}

#Preview {
    AuthContentView(
        content: AuthContentKt.AuthContent_PreviewData()
    )
}
