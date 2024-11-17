//
//  TrailerView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Views
internal import NukeUI

struct TrailerItemView: View {
    @State var imageSize = CGSize.zero
    
    let item: TrailerItem
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            ChildSizeReader(size: $imageSize) {
                LazyImage(url: URL(string: item.thumbnailUrl)) { state in
                    if let image = state.image {
                        image.resizable()
                    } else {
                        Color.gray
                    }
                }
                .aspectRatio(16 / 9, contentMode: .fill) // You need the size of this view
            }
            
            Text(item.titleText)
                .padding()
                .frame(width: imageSize.width, alignment: .leading)
                .lineLimit(2)
                .multilineTextAlignment(.leading)
        }
        .card()
        .onTapGesture {
            item.click()
        }
    }
}

struct ChildSizeReader<Content: View>: View {
    @Binding var size: CGSize
    
    let content: () -> Content
    
    var body: some View {
        content()
            .background(
                GeometryReader {
                    Color.red.preference(
                        key: SizePreferenceKey.self,
                        value: $0.size
                    )
                }
            )
            .onPreferenceChange(SizePreferenceKey.self) {
                size = $0
            }
    }
}

struct SizePreferenceKey: PreferenceKey {
    static var defaultValue = CGSize.zero
    static func reduce(value _: inout Value, nextValue: () -> Value) { }
}

#Preview {
    VStack {
        TrailerItemView(
            item: TrailerItem(
                titleText: "Trailer text",
                thumbnailUrl: "https://img.youtube.com/vi/uLN9qrJ8ESs/0.jpg",
                externalUrl: "",
                onClick: { _ in }
            )
        )
    }
    .frame(height: 200)
}
