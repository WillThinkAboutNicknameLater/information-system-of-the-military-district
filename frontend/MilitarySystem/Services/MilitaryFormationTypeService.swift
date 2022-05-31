//
//  MilitaryFormationTypeService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import Foundation

final class MilitaryFormationTypeService: EntityService<MilitaryFormationType> {
    init() {
        super.init(path: "/military-formation-types")
    }
}
