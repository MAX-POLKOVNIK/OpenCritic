//
//  IosRouter.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI
import UIKit
import MvvmBase

public class IosRouter: ObservableObject, Router {
    let viewModelStore = ViewModelStore()
    
    let creators = ScreenCreators.companion.registered()
    
    @Published var path: [Dest] = [] {
        willSet(newValue) {
            let routes = Set(path).subtracting(newValue)
            
            routes.forEach {
                viewModelStore.clear(for: $0)
            }
        }
    }
    
    @ViewBuilder func view(for destination: Dest) -> some View {
        createView(by: creators.find(route: destination.route), with: destination.args)
    }
    
    public func navigateBack() {
        path.removeLast()
    }
    
    public func navigateTo(destination: Dest) {
        let route = destination.route
        
        if let _ = route as? UrlRoute,
            let urlArgs = destination.args as? UrlRoute.InitArgs,
            let url = URL(string: urlArgs.url) {
            UIApplication.shared.open(url)
            
            return
        }
        
        if let _ = route as? ShareLinkRoute, let args = destination.args as? ShareLinkRoute.InitArgs {
            let text = args.url
            
            if let vc = UIViewController.getCurrentVC() {
                // set up activity view controller
                let textToShare = [text]
                let activityViewController = UIActivityViewController(activityItems: textToShare, applicationActivities: nil)
                
                if UIDevice.current.userInterfaceIdiom == .pad {
                    activityViewController
                        .popoverPresentationController?.sourceView = UIApplication.shared.windows.first
                    
                    activityViewController.popoverPresentationController?.sourceRect =
                        CGRect(
                            x: UIScreen.main.bounds.width / 2,
                            y: UIScreen.main.bounds.height,
                            width: 0,
                            height: 0
                        )
                    activityViewController.popoverPresentationController?.permittedArrowDirections = UIPopoverArrowDirection.down
                    vc.present(activityViewController, animated: true, completion: nil)
                }
                
                vc.present(activityViewController, animated: true)
            }
            
            return
        }
        
        path.append(destination)
    }
    
    public func popToRoot() {
        path.removeLast(path.count)
    }
}

extension Array where Element: Hashable {
    func difference(from other: [Element]) -> [Element] {
        let thisSet = Set(self)
        let otherSet = Set(other)
        return Array(thisSet.symmetricDifference(otherSet))
    }
}
