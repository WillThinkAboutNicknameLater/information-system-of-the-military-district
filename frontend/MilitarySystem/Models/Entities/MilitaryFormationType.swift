//
//  MilitaryFormationType.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryFormationType: Entity {
    var id: UInt16
    
    var name: String
    
    static func < (lhs: MilitaryFormationType, rhs: MilitaryFormationType) -> Bool {
        return lhs.id < rhs.id
    }
}
