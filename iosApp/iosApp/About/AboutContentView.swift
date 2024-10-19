//
//  AboutContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AboutContentView: View {
    let content: AboutContent
    
    var body: some View {
        List {
            Section(header: Text(content.disclosureTitleText)) {
                Text(content.disclosureText)
            }
            Section(header: Text(content.linksTitleText)) {
                ForEach(content.links, id: \.self) { link in
                    HStack {
                        Text(link.title)
                        Spacer()
                    }
                    .contentShape(Rectangle())
                    .onTapGesture { link.onClick() }
                }
            }
            
            Text(content.appVersionText)
        }
    }
}

#Preview {
    AboutContentView(
        content: AboutContentKt.AboutContent_PreviewData()
    )
}
