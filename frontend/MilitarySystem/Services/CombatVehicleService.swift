//
//  CombatVehicleService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class CombatVehicleService: EntityService<CombatVehicle> {
    init() {
        super.init(path: "/combat-vehicles")
    }
}
