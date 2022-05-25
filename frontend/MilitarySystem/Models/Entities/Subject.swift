//
//  Subject.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct Subject: Entity {
    var id: UInt16
    
    var name: String
    
    static func < (lhs: Subject, rhs: Subject) -> Bool {
        return lhs.id < rhs.id
    }
}
