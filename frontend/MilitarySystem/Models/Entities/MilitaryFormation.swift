//
//  MilitaryFormation.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryFormation: Entity {
    var id: UInt32 = 0

    var name: String = ""
    
    var militaryFormationTypeName: String = ""
    
    var dateOfFormation: Date = Date()
    
    var commander: SimplifiedMilitaryMan = SimplifiedMilitaryMan()
    
    var dislocation: SimplifiedDislocation = SimplifiedDislocation()
    
    enum Field: String {
        case id = "ID"
        case name = "Название"
        case militaryFormationTypeName = "Тип"
        case dateOfFormation = "Дата формирования"
        case commanderIdentificationNumber = "Идентификационный номер командира"
        case commanderFullName = "Командир"
        case dislocationOkato = "ОКАТО"
        case dislocationName = "Дислокация"
    }
    
    static func < (lhs: MilitaryFormation, rhs: MilitaryFormation) -> Bool {
        return lhs.id < rhs.id
    }
}
