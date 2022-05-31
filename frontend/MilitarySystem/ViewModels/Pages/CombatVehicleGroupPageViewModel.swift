//
//  CombatVehicleGroupPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class CombatVehicleGroupPageViewModel: EntityPageViewModel<CombatVehicleGroup> {
    init() {
        super.init(service: CombatVehicleGroupService(), entityFilter: CombatVehicleGroupFilter())
    }
}
