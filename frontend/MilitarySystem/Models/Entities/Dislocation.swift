//
//  Dislocation.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct Dislocation: Entity {
    var id: UInt32 = 0

    var name: String = ""

    var dislocationTypeName: String = ""

    var subjectName: String = ""

    var okato: String = ""
    
    enum Field: String {
        case id = "ID"
        case name = "Название"
        case dislocationTypeName = "Тип"
        case subjectName = "Субъект"
        case okato = "ОКАТО"
    }
    
    static func < (lhs: Dislocation, rhs: Dislocation) -> Bool {
        return lhs.id < rhs.id
    }
}
