//
//  CommonScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CommonScreenView<Content: AnyObject, ContentView>: View where ContentView: View {
    let state: CommonViewModelState<Content>
    
    @ViewBuilder let viewProducer: (Content) -> ContentView
    
    var body: some View {
        ZStack {
            if let fullScreenError = state.fullScreenError {
                ErrorStateView(state: fullScreenError)
            }
            
            if let fullScreenLoading = state.fullScreenLoading {
                LoadingStateView(state: fullScreenLoading)
            }
            
            if let content = state.content {
                viewProducer(content)
            }
            
            let _ = handleLoadingPopup(loadingPopup: state.loadingPopup)
            
            let _ = handleErrorPopup(state: state.errorPopup)
            
            if state.toast != nil {
                VStack {
                    Spacer()
                    
                    Text(state.toast?.text.text() ?? "")
                        .padding()
                        .card()
                        .padding(.vertical, 24)
                }
            }
        }
        .navigationBarTitle(state.title, displayMode: .large)
    }
}

// Жесткие хаки. Надо от этого уйти
private var displayedLoadingAlert: UIViewController?
private var displayedErrorPopup: UIViewController?

private func handleLoadingPopup(loadingPopup: BaseLoadingState?) {
    if let loadingPopup = loadingPopup {
        let vc = createProgressAlertViewController(loadingPopup: loadingPopup)
        
        displayedLoadingAlert = vc
        UIViewController.getCurrentVC()?.present(vc, animated: true)
    } else {
        displayedLoadingAlert?.dismiss(animated: false)
        displayedLoadingAlert = nil
    }
}

private func handleErrorPopup(state: BaseErrorState?) {
    if let state = state {
        let vc = createErrorPopupViewController(state: state)
        
        displayedErrorPopup = vc
        
        // workaround to present after loading
        DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
            UIViewController.getCurrentVC()?.present(vc, animated: true)
        }
    } else {
        displayedErrorPopup?.dismiss(animated: false)
        displayedErrorPopup = nil
    }
}


extension UIViewController {
    class func getCurrentVC() -> UIViewController? {
        UIApplication.shared.windows.first!.rootViewController!
    }
}
