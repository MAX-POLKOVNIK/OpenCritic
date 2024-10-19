//
//  DashboardScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class DashboardScreenCreator: ScreenCreator<DashboardRoute.InitArgs> {
    public override var route: any Route { DashboardRoute.shared }
    
    public override func view(args: DashboardRoute.InitArgs) -> Any {
        DashboardScreenView(args: args)
    }
}
