//
//  StaffCategory.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct StaffCategory: Entity {
    var id: UInt16
    
    var name: String
    
    static func < (lhs: StaffCategory, rhs: StaffCategory) -> Bool {
        return lhs.id < rhs.id
    }
}
