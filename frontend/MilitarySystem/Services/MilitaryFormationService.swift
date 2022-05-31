//
//  MilitaryFormationService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import Foundation

final class MilitaryFormationService: EntityService<MilitaryFormation> {
    init() {
        super.init(path: "/military-formations", decoder: CustomDateJSONDecoder())
    }
}
