//
//  MilitaryMan.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryMan: Entity {
    var id: UInt32
    
    var secondName: String
    
    var firstName: String
    
    var patronymic: String
    
    var dateOfBirth: Date
    
    var identificationNumber: String
    
    var rankName: String
    
    var dateOfAward: Date
    
    static func < (lhs: MilitaryMan, rhs: MilitaryMan) -> Bool {
        return lhs.id < rhs.id
    }
}
