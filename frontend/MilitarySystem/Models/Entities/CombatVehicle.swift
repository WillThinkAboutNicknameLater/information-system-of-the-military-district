//
//  CombatVehicle.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct CombatVehicle: Entity {
    var id: UInt32

    var name: String
    
    var combatVehicleCategoryName: String

    var serialNumber: String

    var militaryFormationName: String
    
    static func < (lhs: CombatVehicle, rhs: CombatVehicle) -> Bool {
        return lhs.id < rhs.id
    }
}
