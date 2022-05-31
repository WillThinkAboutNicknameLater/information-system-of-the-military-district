//
//  MilitaryBuildingService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class MilitaryBuildingService: EntityService<MilitaryBuilding> {
    init() {
        super.init(path: "/military-buildings")
    }
}
