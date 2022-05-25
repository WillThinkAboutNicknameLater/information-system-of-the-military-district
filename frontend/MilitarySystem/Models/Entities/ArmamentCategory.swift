//
//  ArmamentCategory.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct ArmamentCategory: Entity {
    var id: UInt16
    
    var name: String
    
    static func < (lhs: ArmamentCategory, rhs: ArmamentCategory) -> Bool {
        return lhs.id < rhs.id
    }
}
