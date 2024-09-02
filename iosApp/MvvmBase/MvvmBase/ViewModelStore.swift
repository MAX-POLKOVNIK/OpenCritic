//
//  ViewModelStore.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

public class ViewModelStore: ObservableObject {
    static var sharedCounter: Int = 0
    let counter: Int
    
    public init() {
        ViewModelStore.sharedCounter += 1
        counter = ViewModelStore.sharedCounter
    }
    
    private var viewModels: [Dest: BaseViewModel<AnyObject>?] = [:]
    
    public func get<I: AnyObject, S: ViewModelState, T: BaseViewModel<S>>(
        for route: Route,
        args: I
    ) -> T {
        let d = Destination<I>(route: route, args: args)
        
        print("Resolving for \(route) in \(counter)")
        
        return get(for: d)
    }
    
    public func get<I: AnyObject, S: ViewModelState, T: BaseViewModel<S>>(
        for destination: Destination<I>
    ) -> T {
        let dest = destination as! Dest
        
        if let viewModel = viewModels[dest] {
            return viewModel as! T
        } else {
            let vm = koinViewModel(T.self, args: [dest.args])
            
            viewModels[dest] = vm as! BaseViewModel<AnyObject>
            return vm
        }
    }
    
    public func clear(for destination: Dest) {
        print("clearing: \(viewModels.keys.count)")
        viewModels[destination] = nil
        print("cleared: \(viewModels.keys.count)")
    }
}

public typealias Dest = Destination<AnyObject>
