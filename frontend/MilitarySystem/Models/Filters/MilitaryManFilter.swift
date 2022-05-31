//
//  MilitaryManFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

struct MilitaryManFilter: EntityFilter {
    var searchName: String?
    
    var rankIds: [UInt16]?
    
    var rankCategoryIds: [UInt16]?
    
    var staffCategoryIds: [UInt16]?
    
    var militarySpecialtyIds: [UInt16]?
    
    var militaryFormationIds: [UInt32]?
    
    enum CodingKeys: String, CodingKey {
        case searchName = "name"
        case rankIds = "rank-id"
        case rankCategoryIds = "rank-category-id"
        case staffCategoryIds = "staff-category-id"
        case militarySpecialtyIds = "specialty-id"
        case militaryFormationIds = "formation-id"
    }
}
