//
//  Request.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 17.05.2022.
//

import Foundation
import SwiftUI

protocol Request {
    associatedtype ReturnType: Codable
    
    var path: String { get }
    
    var method: HTTPMethod { get }
    
    var contentType: HTTPContentType { get }
    
    var queryParams: [String: Any]? { get }
    
    var body: [String: Any]? { get }
    
    var headers: [String: String]? { get }
    
    var decoder: JSONDecoder? { get }
}
