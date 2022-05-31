//
//  CombarVehiclePageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class CombatVehiclePageViewModel: EntityPageViewModel<CombatVehicle> {
    init() {
        super.init(service: CombatVehicleService(), entityFilter: CombatVehicleFilter())
    }
}
