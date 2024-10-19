//
//  ArticleScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class ArticleScreenCreator: ScreenCreator<ArticleRoute.InitArgs> {
    public override var route: any Route { ArticleRoute.shared }
    
    public override func view(args: ArticleRoute.InitArgs) -> Any {
        ArticleScreenView(args: args)
    }
}
