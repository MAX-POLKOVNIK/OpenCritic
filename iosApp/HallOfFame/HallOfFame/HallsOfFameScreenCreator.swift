//
//  HallsOfFameScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class HallsOfFameScreenCreator: ScreenCreator<HallOfFameRoute.InitArgs> {
    public override var route: any Route { HallOfFameRoute.shared }
    
    public override func view(args: HallOfFameRoute.InitArgs) -> Any {
        HallsOfFameScreenView(args: args)
    }
}
