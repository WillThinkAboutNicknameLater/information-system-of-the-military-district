//
//  MilitaryMan.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct MilitaryMan: Entity {
    var id: UInt32 = 0
    
    var secondName: String = ""
    
    var firstName: String = ""
    
    var patronymic: String = ""
    
    var dateOfBirth: Date = Date()
    
    var identificationNumber: String = ""
    
    var rankName: String = ""
    
    var dateOfAward: Date = Date()
    
    enum Field: String {
        case id = "ID"
        case secondName = "Фамилия"
        case firstName = "Имя"
        case patronymic = "Отчество"
        case dateOfBirth = "Дата рождения"
        case identificationNumber = "Идентификационный номер"
        case rankName = "Звание"
        case dateOfAward = "Дата получения звания"
    }
    
    static func < (lhs: MilitaryMan, rhs: MilitaryMan) -> Bool {
        return lhs.id < rhs.id
    }
}
