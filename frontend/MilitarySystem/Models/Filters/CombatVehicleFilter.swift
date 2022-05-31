//
//  CombatVehicleFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

struct CombatVehicleFilter: EntityFilter {
    var searchName: String?
    
    var combatVehicleCategoryIds: [UInt16]?
    
    var combatVehicleGroupIds: [UInt16]?
    
    var militaryFormationIds: [UInt32]?
    
    enum CodingKeys: String, CodingKey {
        case searchName = "name"
        case combatVehicleCategoryIds = "category-id"
        case combatVehicleGroupIds = "group-id"
        case militaryFormationIds = "formation-id"
    }
}
