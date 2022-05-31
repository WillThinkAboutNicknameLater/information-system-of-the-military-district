//
//  EntityService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 24.05.2022.
//

import Foundation
import Combine

class EntityService<T: Entity> {
    private let apiClient = APIClient(baseURL: APIConfig.API_BASE_URL)
    
    private let path: String
    
    private let decoder: JSONDecoder
    
    init(path: String, decoder: JSONDecoder = JSONDecoder()) {
        self.path = path
        self.decoder = decoder
    }
    
    func getAll() -> AnyPublisher<[T], NetworkRequestError> {
        return apiClient.dispatch(FindAllEntitiesRequest(path: path, decoder: decoder))
    }
    
    func getAllByFilter(entityFilter: EntityFilter, pageFilter: PageFilter = PageFilter()) -> AnyPublisher<EntityPage<T>, NetworkRequestError> {
        return apiClient.dispatch(FindAllEntitiesByFilterRequest(path: path, decoder: decoder, entityFilter: entityFilter, pageFilter: pageFilter))
    }
    
    func create(entity: T) -> AnyPublisher<T, NetworkRequestError> {
        return apiClient.dispatch(PostEntityRequest(path: path, decoder: decoder, entity: entity))
    }
    
    func update(entity: T) -> AnyPublisher<T, NetworkRequestError> {
        return apiClient.dispatch(PutEntityRequest(path: path, decoder: decoder, entity: entity))
    }
    
    func delete(entity: T) -> AnyPublisher<T, NetworkRequestError> {
        return apiClient.dispatch(DeleteEntityRequest(path: path, decoder: decoder, entity: entity))
    }
}
