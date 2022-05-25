//
//  RankCategory.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct RankCategory: Entity {
    var id: UInt16
    
    var name: String
    
    static func < (lhs: RankCategory, rhs: RankCategory) -> Bool {
        return lhs.id < rhs.id
    }
}
