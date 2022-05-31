//
//  MilitaryFormationType.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryFormationType: Entity {
    var id: UInt16 = 0
    
    var name: String = ""
    
    enum Field: String {
        case id = "ID"
        case name = "Название"
    }
    
    static func < (lhs: MilitaryFormationType, rhs: MilitaryFormationType) -> Bool {
        return lhs.id < rhs.id
    }
}
