//
//  FindAllEntitiesRequest.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

class FindAllEntitiesRequest<T: Entity>: Request {
    typealias ReturnType = [T]
    
    var path: String
    
    var decoder: JSONDecoder?
    
    init(path: String, decoder: JSONDecoder? = JSONDecoder()) {
        self.path = path
        self.decoder = decoder
    }
}
