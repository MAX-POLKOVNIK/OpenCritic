//
//  AuthScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class AuthScreenCreator: ScreenCreator<AuthRoute.InitArgs> {
    public override var route: any Route { AuthRoute.shared }
    
    public override func view(args: AuthRoute.InitArgs) -> Any {
        AuthScreenView(args: args)
    }
}
