//
//  ScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

@ViewBuilder 
public func createView<InitArgs: AnyObject>(
    by creator: ScreenCreator<InitArgs>,
    with args: InitArgs
) -> AnyView {
    let view = creator.view(args: args) as! any View
    
    AnyView(view)
}

public func anyCreator<InitArgs: AnyObject>(
    of creator: ScreenCreator<InitArgs>
) -> ScreenCreator<AnyObject> {
    creator as! ScreenCreator<AnyObject>
}
