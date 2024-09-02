//
//  AuthorReviewsScreenCreator.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class AuthorReviewsScreenCreator: ScreenCreator<AuthorReviewsRoute.InitArgs> {
    public override var route: any Route { AuthorReviewsRoute.shared }
    
    public override func view(args: AuthorReviewsRoute.InitArgs) -> Any {
        AuthorReviewsScreenView(args: args)
    }
}
