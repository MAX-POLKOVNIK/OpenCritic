//
//  GameListsScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class GameListsScreenCreator: ScreenCreator<GameListsRoute.InitArgs> {
    public override var route: any Route { GameListsRoute.shared }
    
    public override func view(args: GameListsRoute.InitArgs) -> Any {
        YourGameListScreenView(args: args)
    }
}
