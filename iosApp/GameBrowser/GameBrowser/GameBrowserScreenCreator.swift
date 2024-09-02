//
//  GameBrowserScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class GameBrowserScreenCreator: ScreenCreator<GameBrowserRoute.InitArgs> {
    public override var route: any Route { GameBrowserRoute.shared }
    
    public override func view(args: GameBrowserRoute.InitArgs) -> Any {
        GameBrowserScreenView(args: args)
    }
}
