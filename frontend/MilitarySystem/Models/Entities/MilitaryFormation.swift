//
//  MilitaryFormation.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryFormation: Entity {
    var id: UInt32

    var name: String
    
    var militaryFormationTypeName: String
    
    var dateOfFormation: Date
    
    var commander: SimplifiedMilitaryMan
    
    var dislocation: SimplifiedDislocation
    
    static func < (lhs: MilitaryFormation, rhs: MilitaryFormation) -> Bool {
        return lhs.id < rhs.id
    }
}
