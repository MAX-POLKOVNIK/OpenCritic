//
//  NukeImagePreloader.swift
//  App
//
//  Created by Max Polkovnik on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
internal import Nuke

class NukeImagePreloader: ImagePreloader {
    private let prefetcher = ImagePrefetcher(destination: .diskCache)
    
    func cancel() {
        prefetcher.stopPrefetching()
    }
    
    func load(urls: [ImageUrl]) {
        prefetcher.startPrefetching(with: urls.map { ImageRequest(url: URL(string: $0.path)) })
    }
}
