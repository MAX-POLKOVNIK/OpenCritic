//
//  SwiftUIWebView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import WebKit
import SwiftUI

struct WebView: UIViewRepresentable {
    
    let url: URL
    let userAgent: String
    let delegate: WKNavigationDelegate

    func makeUIView(context: Context) -> WKWebView {
        let configuration = WKWebViewConfiguration()
        configuration.applicationNameForUserAgent = userAgent
        
        let webView = WKWebView(frame: .zero, configuration: configuration)
        
        webView.customUserAgent = userAgent
        webView.navigationDelegate = delegate
        
        return webView
    }
    
    func updateUIView(_ webView: WKWebView, context: Context) {
        let request = URLRequest(url: url)
        webView.load(request)
    }
}

