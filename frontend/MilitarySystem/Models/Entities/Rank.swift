//
//  Rank.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct Rank: Entity {
    var id: UInt16
    
    var name: String
    
    var rankCategoryName: String
    
    var staffCategoryName: String
    
    static func < (lhs: Rank, rhs: Rank) -> Bool {
        return lhs.id < rhs.id
    }
}
