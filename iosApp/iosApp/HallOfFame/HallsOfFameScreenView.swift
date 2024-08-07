//
//  HallsOfFameScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 07/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HallsOfFameScreenView: View {
    let viewModel: HallsOfFameViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            CommonScreenView(state: state) { content in
                HallsOfFameContentView(content: content)
            }
        }
    }
}
