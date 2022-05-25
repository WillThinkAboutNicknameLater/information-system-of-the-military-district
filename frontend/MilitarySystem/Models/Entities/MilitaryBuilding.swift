//
//  MilitaryBuilding.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryBuilding: Entity {
    var id: UInt32

    var name: String
    
    var militaryBuildingCategoryName: String

    var militaryFormationName: String
    
    static func < (lhs: MilitaryBuilding, rhs: MilitaryBuilding) -> Bool {
        return lhs.id < rhs.id
    }
}
