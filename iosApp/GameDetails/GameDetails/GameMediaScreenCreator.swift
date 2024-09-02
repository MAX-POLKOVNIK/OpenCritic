//
//  GameMediaScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class GameMediaScreenCreator: ScreenCreator<GameMediaRoute.InitArgs> {
    public override var route: any Route { GameMediaRoute.shared }
    
    public override func view(args: GameMediaRoute.InitArgs) -> Any {
        GameMediaScreenView(args: args)
    }
}
