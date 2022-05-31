//
//  EntityPage.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

struct EntityPage<T: Entity>: Codable {
    var content: [T] = []
    
    var number: UInt = 0

    var totalPages: UInt = 0

    var totalElements: UInt = 0
    
    var isFirst: Bool = true
    
    var isLast: Bool = true
    
    enum CodingKeys: String, CodingKey {
        case content
        case number
        case totalPages
        case totalElements
        case isFirst = "first"
        case isLast = "last"
    }
}
