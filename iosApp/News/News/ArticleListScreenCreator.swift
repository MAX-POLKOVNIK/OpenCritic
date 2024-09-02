//
//  ArticleListScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

public class ArticleListScreenCreator: ScreenCreator<ArticleListRoute.InitArgs> {
    public override var route: any Route { ArticleListRoute.shared }
    
    public override func view(args: ArticleListRoute.InitArgs) -> Any {
        ArticleListScreenView(args: args)
    }
}
