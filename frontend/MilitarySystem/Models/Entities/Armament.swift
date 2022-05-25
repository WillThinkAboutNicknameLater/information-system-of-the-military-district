//
//  Armament.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct Armament: Entity {
    var id: UInt32

    var name: String

    var armamentCategoryName: String

    var serialNumber: String

    var militaryFormationName: String
    
    static func < (lhs: Armament, rhs: Armament) -> Bool {
        return lhs.id < rhs.id
    }
}
