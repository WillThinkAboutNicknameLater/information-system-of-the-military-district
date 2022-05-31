//
//  MilitaryFormationFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

struct MilitaryFormationFilter: EntityFilter {
    var searchName: String?
    
    var militaryFormationTypeIds: [UInt16]?
    
    enum CodingKeys: String, CodingKey {
        case searchName = "name"
        case militaryFormationTypeIds = "type-id"
    }
}
