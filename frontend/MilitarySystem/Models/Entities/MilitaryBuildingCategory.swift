//
//  MilitaryBuildingCategory.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryBuildingCategory: Entity {
    var id: UInt16
    
    var name: String
    
    static func < (lhs: MilitaryBuildingCategory, rhs: MilitaryBuildingCategory) -> Bool {
        return lhs.id < rhs.id
    }
}
