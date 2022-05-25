//
//  HTTPStatus.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 17.05.2022.
//

import Foundation

enum HTTPStatus: String, Codable {
    case ok = "OK"
    case created = "CREATED"
    case badRequest = "BAD_REQUEST"
    case notFound = "NOT_FOUND"
    case methodNotAllowed = "METHOD_NOT_ALLOWED"
    case conflict = "CONFLICT"
    case internalServerError = "INTERNAL_SERVER_ERROR"
    
    var code: Int {
        switch self {
        case .ok:
            return 200
        case .created:
            return 201
        case .badRequest:
            return 400
        case .notFound:
            return 404
        case .methodNotAllowed:
            return 405
        case .conflict:
            return 409
        case .internalServerError:
            return 500
        }
    }
    
}
