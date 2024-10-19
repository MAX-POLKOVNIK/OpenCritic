//
//  AboutScreen.swift
//  iosApp
//
//  Created by Max Polkovnik on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AboutScreenView: View {
    let viewModel: AboutViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            CommonScreenView(state: state) { content in
                AboutContentView(content: content)
            }
        }
    }
}
