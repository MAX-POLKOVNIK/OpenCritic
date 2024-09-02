//
//  SearchScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class SearchScreenCreator: ScreenCreator<SearchRoute.InitArgs> {
    public override var route: any Route { SearchRoute.shared }
    
    public override func view(args: SearchRoute.InitArgs) -> Any {
        SearchScreenView(args: args)
    }
}
