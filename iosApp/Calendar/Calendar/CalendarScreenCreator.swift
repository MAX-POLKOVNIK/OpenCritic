//
//  CalendarScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class CalendarScreenCreator: ScreenCreator<CalendarRoute.InitArgs> {
    public override var route: any Route { CalendarRoute.shared }
    
    public override func view(args: CalendarRoute.InitArgs) -> Any {
        CalendarScreenView(args: args)
    }
}
