//
//  CombatVehicleGroup.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct CombatVehicleGroup: Entity {
    var id: UInt16 = 0

    var name: String = ""
    
    enum Field: String {
        case id = "ID"
        case name = "Название"
    }
    
    static func < (lhs: CombatVehicleGroup, rhs: CombatVehicleGroup) -> Bool {
        return lhs.id < rhs.id
    }
}
