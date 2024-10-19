//
//  GameReviewsScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class GameReviewsScreenCreator: ScreenCreator<GameReviewsRoute.InitArgs> {
    public override var route: any Route { GameReviewsRoute.shared }
    
    public override func view(args: GameReviewsRoute.InitArgs) -> Any {
        GameReviewsScreenView(args: args)
    }
}
