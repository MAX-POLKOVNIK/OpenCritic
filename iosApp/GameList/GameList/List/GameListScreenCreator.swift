//
//  GameListScreen.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class GameListScreenCreator: ScreenCreator<GameListRoute.InitArgs> {
    public override var route: any Route { GameListRoute.shared }
    
    public override func view(args: GameListRoute.InitArgs) -> Any {
        GameListScreenView(args: args)
    }
}
