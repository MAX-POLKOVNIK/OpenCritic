//
//  DashboardStateErrorView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardStateErrorView: View {
    let state: DashboardStateError
    
    var body: some View {
        Text(state.error)
    }
}

#Preview {
    DashboardStateErrorView(
        state: DashboardStateError(error: "Test error")
    )
}
