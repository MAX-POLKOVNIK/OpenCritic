//
//  KoinViewModel.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

func koinViewModel<S: ViewModelState, T: BaseViewModel<S>>(_ t: T.Type = T.self, args: [Any] = []) -> T {
    KoinViewModelKt.koinViewModel(T.self, args: args) as! T
}
