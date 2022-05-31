//
//  PostEntityRequest.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 26.05.2022.
//

import Foundation

class PostEntityRequest<T: Entity>: Request {
    typealias ReturnType = T
    
    var path: String
    
    var body: [String : Any]?
    
    var method: HTTPMethod = .post
    
    var decoder: JSONDecoder?
    
    init(path: String, decoder: JSONDecoder? = JSONDecoder(), entity: T) {
        self.path = path
        self.decoder = decoder
        self.body = entity.asDictionary
    }
}
