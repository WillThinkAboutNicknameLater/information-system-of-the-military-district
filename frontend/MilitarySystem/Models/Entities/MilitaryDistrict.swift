//
//  MilitaryDistrict.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryDistrict: Entity {
    var id: UInt16 = 0

    var name: String = ""
    
    var dateOfFormation: Date = Date()
    
    var commander: SimplifiedMilitaryMan = SimplifiedMilitaryMan()
    
    var headquartersDislocation: SimplifiedDislocation = SimplifiedDislocation()
    
    enum Field: String {
        case id = "ID"
        case name = "Название"
        case dateOfFormation = "Дата формирования"
        case commanderIdentificationNumber = "Идентификационный номер командира"
        case commanderFullName = "Командир"
        case headquartersDislocationOkato = "ОКАТО"
        case headquartersDislocationName = "Штаб"
    }
    
    static func < (lhs: MilitaryDistrict, rhs: MilitaryDistrict) -> Bool {
        return lhs.id < rhs.id
    }
}
