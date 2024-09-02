//
//  OutletReviewsScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class OutletReviewsScreenCreator: ScreenCreator<OutletReviewsRoute.InitArgs> {
    public override var route: any Route { OutletReviewsRoute.shared }
    
    public override func view(args: OutletReviewsRoute.InitArgs) -> Any {
        OutletReviewsScreenView(args: args)
    }
}
