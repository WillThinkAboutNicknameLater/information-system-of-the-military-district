//
//  MilitarySpecialty.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitarySpecialty: Entity {
    var id: UInt16
    
    var name: String
    
    static func < (lhs: MilitarySpecialty, rhs: MilitarySpecialty) -> Bool {
        return lhs.id < rhs.id
    }
}
