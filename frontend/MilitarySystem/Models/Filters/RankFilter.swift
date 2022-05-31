//
//  RankFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

struct RankFilter: EntityFilter {
    var searchName: String?
    
    var rankCategoryIds: [UInt16]?
    
    var staffCategoryIds: [UInt16]?
    
    enum CodingKeys: String, CodingKey {
        case searchName = "name"
        case rankCategoryIds = "rank-category-id"
        case staffCategoryIds = "staff-category-id"
    }
}
