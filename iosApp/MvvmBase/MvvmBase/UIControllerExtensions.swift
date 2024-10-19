//
//  UIControllerExtensions.swift
//  MvvmBase
//
//  Created by Max Polkovnik on 04.09.2024.
//

import Foundation
import UIKit

public extension UIViewController {
    class func getCurrentVC() -> UIViewController? {
        UIApplication.shared.windows.first!.rootViewController!
    }
}
