//
//  FindAllEntitiesByFilterRequest.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

class FindAllEntitiesByFilterRequest<T: Entity>: Request {
    typealias ReturnType = EntityPage<T>
    
    var path: String
    
    var queryParams: [String : Any]?
    
    var decoder: JSONDecoder?
    
    init(path: String, decoder: JSONDecoder? = JSONDecoder(), entityFilter: EntityFilter, pageFilter: PageFilter) {
        self.path = "\(path)/search"
        self.decoder = decoder
        self.queryParams = pageFilter.asDictionary
    }
}
