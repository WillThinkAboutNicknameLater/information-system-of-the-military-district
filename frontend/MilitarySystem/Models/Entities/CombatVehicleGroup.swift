//
//  CombatVehicleGroup.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct CombatVehicleGroup: Entity {
    var id: UInt16

    var name: String
    
    static func < (lhs: CombatVehicleGroup, rhs: CombatVehicleGroup) -> Bool {
        return lhs.id < rhs.id
    }
}
