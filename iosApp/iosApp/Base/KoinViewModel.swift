//
//  KoinViewModel.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

func koinViewModel<S: ViewModelState, T: BaseViewModel<S>>(_ t: T.Type, arg: Any = Void()) -> T {
    KoinViewModelKt.koinViewModel(T.self, arg: arg) as! T
}
