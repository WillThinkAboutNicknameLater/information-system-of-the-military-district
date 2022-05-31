//
//  Rank.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct Rank: Entity {
    var id: UInt16 = 0
    
    var name: String = ""
    
    var rankCategoryName: String = ""
    
    var staffCategoryName: String = ""
    
    enum Field: String {
        case id = "ID"
        case name = "Название"
        case rankCategoryName = "Категория звания"
        case staffCategoryName = "Категория состава"
    }
    
    static func < (lhs: Rank, rhs: Rank) -> Bool {
        return lhs.id < rhs.id
    }
}
