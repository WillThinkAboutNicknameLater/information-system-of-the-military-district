//
//  CombatVehicleCategory.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct CombatVehicleCategory: Entity {
    var id: UInt16 = 0

    var name: String = ""

    var combatVehicleGroupName: String = ""
    
    enum Field: String {
        case id = "ID"
        case name = "Название"
        case combatVehicleGroupName = "Группа"
    }
    
    static func < (lhs: CombatVehicleCategory, rhs: CombatVehicleCategory) -> Bool {
        return lhs.id < rhs.id
    }
}
