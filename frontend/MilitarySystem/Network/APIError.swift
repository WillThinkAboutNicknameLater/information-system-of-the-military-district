//
//  APIError.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 17.05.2022.
//

import Foundation

struct APIError: Codable, Error {
    var httpStatus: HTTPStatus
    
    var timestamp: Date
    
    var title: String
    
    var message: String
    
    enum CodingKeys: String, CodingKey {
        case httpStatus
        case timestamp
        case title
        case message
    }
}
