//
//  DeleteEntityRequest.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

class DeleteEntityRequest<T: Entity>: Request {
    typealias ReturnType = T
    
    var path: String
    
    var body: [String : Any]?
    
    var method: HTTPMethod = .delete
    
    var decoder: JSONDecoder?
    
    init(path: String, decoder: JSONDecoder? = JSONDecoder(), entity: T) {
        self.path = "\(path)/\(entity.id)"
        self.decoder = decoder
        self.body = entity.asDictionary
    }
}
