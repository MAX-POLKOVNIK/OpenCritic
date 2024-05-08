//
//  FlowPublisher.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

func FlowPublisher<T>(_ flow: ViewModelStateFlow<T>) -> AnyPublisher<T, Never> {
    return Deferred<Publishers.HandleEvents<CurrentValueSubject<T, Never>>> {
        let subject = CurrentValueSubject<T, Never>(flow.value)
        
        let closable = flow.watch { next in
            subject.send(next)
        }
        
        return subject.handleEvents(
            receiveCancel: {
                closable.close()
            }
        )
    }.eraseToAnyPublisher()
}
