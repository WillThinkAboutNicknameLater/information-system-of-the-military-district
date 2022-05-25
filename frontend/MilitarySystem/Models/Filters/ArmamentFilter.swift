//
//  ArmamentFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

struct ArmamentFilter: EntityFilter {
    var searchName: String?
    
    var armamentCategoryIds: [UInt16]?
    
    var militaryFormationIds: [UInt32]?
    
    enum CodingKeys: String, CodingKey {
        case searchName = "name"
        case armamentCategoryIds = "category-id"
        case militaryFormationIds = "formation-id"
    }
}
