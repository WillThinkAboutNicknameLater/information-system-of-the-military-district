//
//  PageFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

struct PageFilter: Codable {
    var pageNumber: UInt32 = 0
    
    var pageSize: UInt32 = 20
    
    enum CodingKeys: String, CodingKey {
        case pageNumber = "page-number"
        case pageSize = "page-size"
    }
}
