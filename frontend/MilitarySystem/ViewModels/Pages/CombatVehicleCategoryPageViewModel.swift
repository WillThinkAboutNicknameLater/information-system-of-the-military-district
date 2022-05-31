//
//  CombatVehicleCategoryPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class CombatVehicleCategoryPageViewModel: EntityPageViewModel<CombatVehicleCategory> {
    init() {
        super.init(service: CombatVehicleCategoryService(), entityFilter: CombatVehicleCategoryFilter())
    }
}
