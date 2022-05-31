//
//  MilitaryBuilding.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryBuilding: Entity {
    var id: UInt32 = 0

    var name: String = ""
    
    var militaryBuildingCategoryName: String = ""

    var militaryFormationName: String = ""
    
    enum Field: String {
        case id = "ID"
        case name = "Название"
        case militaryBuildingCategoryName = "Категория"
        case militaryFormationName = "Воинское формирование"
    }
    
    static func < (lhs: MilitaryBuilding, rhs: MilitaryBuilding) -> Bool {
        return lhs.id < rhs.id
    }
}
