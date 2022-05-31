//
//  ArmamentCategoryFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

struct ArmamentCategoryFilter: EntityFilter {    
    var searchName: String?
    
    enum CodingKeys: String, CodingKey {
        case searchName = "name"
    }
}
