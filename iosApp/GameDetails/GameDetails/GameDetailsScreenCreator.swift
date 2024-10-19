//
//  GameDetailsScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class GameDetailsScreenCreator: ScreenCreator<GameDetailsRoute.InitArgs> {
    public override var route: any Route { GameDetailsRoute.shared }
    
    public override func view(args: GameDetailsRoute.InitArgs) -> Any {
        GameDetailsScreenView(args: args)
    }
}
