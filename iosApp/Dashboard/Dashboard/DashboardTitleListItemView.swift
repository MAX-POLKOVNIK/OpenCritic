//
//  DashboardTitleListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardTitleListItemView: View {
    let item: DashboardTitleListItem
    
    var body: some View {
        HStack {
            VStack(
                alignment: .leading,
                content: {
                    Text(item.titleText)
                        .font(.title)
                    if item.isDescriptionVisible {
                        Text(item.subtitleText)
                    }
                }
            )
            
            Spacer()
            
            if let buttonTitle = item.buttonTitle {
                Button(buttonTitle) {
                    item.onButtonClick()
                }
            }
        }
        .padding(.horizontal)
    }
}

struct DashboardTitleListItemView_Previews: PreviewProvider {
    static var previews: some View {
        DashboardTitleListItemView(
            item: DashboardTitleListItem(
                title: "Hello".asTextSource(),
                subtitle: "Second string".asTextSource(),
                buttonTitle: "View all".asTextSource(),
                onButtonClick: {}
            )
        )
        .previewLayout(.fixed(width: 300, height: 600))
    }
}
