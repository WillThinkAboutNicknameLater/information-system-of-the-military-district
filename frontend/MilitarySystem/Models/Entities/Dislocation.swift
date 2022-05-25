//
//  Dislocation.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct Dislocation: Entity {
    var id: UInt32

    var name: String

    var dislocationTypeName: String

    var subjectName: String

    var okato: String
    
    static func < (lhs: Dislocation, rhs: Dislocation) -> Bool {
        return lhs.id < rhs.id
    }
}
