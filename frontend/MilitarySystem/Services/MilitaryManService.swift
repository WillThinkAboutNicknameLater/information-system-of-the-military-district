//
//  MilitaryManService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 16.05.2022.
//

import Foundation

final class MilitaryManService: EntityService<MilitaryMan> {
    init() {
        super.init(path: "/military-men", decoder: CustomDateJSONDecoder())
    }
}
