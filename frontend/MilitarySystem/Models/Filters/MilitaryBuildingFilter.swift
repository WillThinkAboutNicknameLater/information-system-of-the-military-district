//
//  MilitaryBuildingFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

struct MilitaryBuildingFilter: EntityFilter {
    var searchName: String?
    
    var militaryBuildingCategoryIds: [UInt16]?
    
    var militaryFormationIds: [UInt32]?
    
    enum CodingKeys: String, CodingKey {
        case searchName = "name"
        case militaryBuildingCategoryIds = "category-id"
        case militaryFormationIds = "formation-id"
    }
}
