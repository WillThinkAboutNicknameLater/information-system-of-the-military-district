//
//  CombatVehicleCategoryService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class CombatVehicleCategoryService: EntityService<CombatVehicleCategory> {
    init() {
        super.init(path: "/combat-vehicle-categories")
    }
}
