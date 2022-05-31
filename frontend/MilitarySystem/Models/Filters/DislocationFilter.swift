//
//  DislocationFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

struct DislocationFilter: EntityFilter {
    var searchName: String?
    
    var dislocationTypeIds: [UInt16]?
    
    var subjectIds: [UInt16]?
    
    enum CodingKeys: String, CodingKey {
        case searchName = "name"
        case dislocationTypeIds = "group-id"
        case subjectIds = "subject-id"
    }
}
