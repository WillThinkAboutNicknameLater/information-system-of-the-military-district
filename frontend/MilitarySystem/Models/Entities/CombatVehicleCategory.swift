//
//  CombatVehicleCategory.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct CombatVehicleCategory: Entity {
    var id: UInt16

    var name: String

    var combatVehicleGroupName: String
    
    static func < (lhs: CombatVehicleCategory, rhs: CombatVehicleCategory) -> Bool {
        return lhs.id < rhs.id
    }
}
