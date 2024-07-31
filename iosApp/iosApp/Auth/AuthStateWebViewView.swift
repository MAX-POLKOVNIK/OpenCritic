//
//  AuthStateWebViewView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import WebKit
import Foundation

struct AuthStateWebViewView: View {
    let state: AuthStateWebView
    
    var body: some View {
        WebView(
            url: URL(string: state.url)!,
            userAgent: state.authUserAgent.description(),
            delegate: NavigationDelegate(predicate: state.redirectHandler)
        )
        .navigationBarTitle("", displayMode: .inline)
    }
}

class NavigationDelegate : NSObject, WKNavigationDelegate {
    
    private let predicate: (String) -> KotlinBoolean
    
    init(predicate: @escaping (String) -> KotlinBoolean) {
        self.predicate = predicate
    }
    
    func webView(_ webView: WKWebView, decidePolicyFor navigationAction: WKNavigationAction, preferences: WKWebpagePreferences, decisionHandler: @escaping (WKNavigationActionPolicy, WKWebpagePreferences) -> Void) {
        if predicate(navigationAction.request.url?.absoluteString ?? "").boolValue {
            decisionHandler(.allow, preferences)
        } else {
            decisionHandler(.cancel, preferences)
        }
    }
    
    func webView(_ webView: WKWebView, decidePolicyFor navigationAction: WKNavigationAction, decisionHandler: @escaping (WKNavigationActionPolicy) -> Void) {
        if predicate(navigationAction.request.url?.absoluteString ?? "").boolValue {
            decisionHandler(.allow)
        } else {
            decisionHandler(.cancel)
        }
    }
    
//    func webView(_ webView: WKWebView, decidePolicyFor navigationResponse: WKNavigationResponse, decisionHandler: @escaping (WKNavigationResponsePolicy) -> Void) {
//        if predicate(navigationAction.request.url?.absoluteString ?? "") {
//            decisionHandler(.allow, preferences)
//        } else {
//            decisionHandler(.cancel, preferences)
//        }
//    }
}

//#Preview {
//    AuthStateWebViewView()
//}
