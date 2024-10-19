//
//  AboutScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class AboutScreenCreator: ScreenCreator<AboutRoute.InitArgs> {
    public override var route: any Route { AboutRoute.shared }
    
    public override func view(args: AboutRoute.InitArgs) -> Any {
        AboutScreenView(args: args)
    }
}
