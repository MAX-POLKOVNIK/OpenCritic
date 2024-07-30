//
//  IosStringResourceProvider.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class IosStringProvider : StringProvider {
    func mapStringDescToString(stringDesc: any StringDesc) -> String {
        stringDesc.localized()
    }
}
