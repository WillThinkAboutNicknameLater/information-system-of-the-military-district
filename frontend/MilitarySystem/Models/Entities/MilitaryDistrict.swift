//
//  MilitaryDistrict.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryDistrict: Entity {
    var id: UInt16

    var name: String
    
    var dateOfFormation: Date
    
    var commander: SimplifiedMilitaryMan
    
    var headquartersDislocation: SimplifiedDislocation
    
    static func < (lhs: MilitaryDistrict, rhs: MilitaryDistrict) -> Bool {
        return lhs.id < rhs.id
    }
}
