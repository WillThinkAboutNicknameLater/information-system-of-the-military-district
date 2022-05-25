//
//  DislocationType.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct DislocationType: Entity {
    var id: UInt16
    
    var name: String
    
    static func < (lhs: DislocationType, rhs: DislocationType) -> Bool {
        return lhs.id < rhs.id
    }
}
