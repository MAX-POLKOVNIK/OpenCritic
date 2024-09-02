//
//  PeriodGameBrowserScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class PeriodGameBrowserScreenCreator: ScreenCreator<PeriodGameBrowserRoute.InitArgs> {
    public override var route: any Route { PeriodGameBrowserRoute.shared }
    
    public override func view(args: PeriodGameBrowserRoute.InitArgs) -> Any {
        PeriodGameBrowserScreenView(args: args)
    }
}
