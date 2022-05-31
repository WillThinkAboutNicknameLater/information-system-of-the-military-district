//
//  CombatVehicleGroupService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class CombatVehicleGroupService: EntityService<CombatVehicleGroup> {
    init() {
        super.init(path: "/combat-vehicle-groups")
    }
}
