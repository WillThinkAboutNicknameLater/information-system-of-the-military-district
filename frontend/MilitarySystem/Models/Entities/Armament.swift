//
//  Armament.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct Armament: Entity {
    var id: UInt32 = 0

    var name: String = ""

    var armamentCategoryName: String = ""

    var serialNumber: String = ""

    var militaryFormationName: String = ""
    
    enum Field: String {
        case id = "ID"
        case name = "Название"
        case armamentCategoryName = "Категория"
        case serialNumber = "Серийный номер"
        case militaryFormationName = "Воинское формирование"
    }
    
    static func < (lhs: Armament, rhs: Armament) -> Bool {
        return lhs.id < rhs.id
    }
}
