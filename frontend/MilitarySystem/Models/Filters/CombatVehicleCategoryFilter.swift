//
//  CombatVehicleCategoryFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

struct CombatVehicleCategoryFilter: EntityFilter {
    var searchName: String?
    
    var combatVehicleGroupIds: [UInt16]?
    
    enum CodingKeys: String, CodingKey {
        case searchName = "name"
        case combatVehicleGroupIds = "group-id"
    }
}
