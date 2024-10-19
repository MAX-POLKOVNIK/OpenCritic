//
//  ProgressView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProgressAlert: View {
    let model: BaseLoadingState

    var body: some View {
        VStack {
            HStack {
                ProgressView()
                
                Text(model.text)
                    .padding(.horizontal)
            }
        }
        .frame(maxWidth: .infinity)
        .padding(.vertical)
        .padding()
        .card()
        .padding()
    }
}

public func createProgressAlertViewController(loadingPopup: BaseLoadingState) -> UIViewController {
    let vc = UIHostingController(rootView: ProgressAlert(model: loadingPopup))
    
    vc.modalPresentationStyle = .overFullScreen
    vc.modalTransitionStyle = .crossDissolve
    vc.view.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 0.4)
    
    return vc
}

#Preview {
    ProgressAlert(
        model: LoadingState()
    )
}
