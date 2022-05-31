//
//  APIClient.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 17.05.2022.
//

import Foundation
import Combine

final class APIClient {
    private var baseURL: String
    
    private var networkDispatcher: NetworkDispatcher
    
    init(baseURL: String, networkDispatcher: NetworkDispatcher = NetworkDispatcher()) {
        self.baseURL = baseURL
        self.networkDispatcher = networkDispatcher
    }
    
    func dispatch<R: Request>(_ request: R) -> AnyPublisher<R.ReturnType, NetworkRequestError> {
        guard let urlRequest = request.asURLRequest(baseURL: baseURL) else {
            return Fail(outputType: R.ReturnType.self, failure: .unknownError).eraseToAnyPublisher()
        }
        
        typealias RequestPublisher = AnyPublisher<R.ReturnType, NetworkRequestError>
        let requestPublisher: RequestPublisher = networkDispatcher.dispatch(request: urlRequest, decoder: request.decoder)
        return requestPublisher.eraseToAnyPublisher()
    }
}
