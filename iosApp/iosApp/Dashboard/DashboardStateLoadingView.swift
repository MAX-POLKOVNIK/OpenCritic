//
//  DashboardStateLoadingView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardStateLoadingView: View {
    let state: DashboardStateLoading
    
    var body: some View {
        Text("Loading...")
    }
}

#Preview {
    DashboardStateLoadingView(
        state: DashboardStateLoading()
    )
}
