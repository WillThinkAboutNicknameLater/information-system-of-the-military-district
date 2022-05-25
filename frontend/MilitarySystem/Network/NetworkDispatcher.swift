//
//  NetworkDispatcher.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 17.05.2022.
//

import Foundation
import Combine

final class NetworkDispatcher {
    private let urlSession: URLSession
    
    public init(urlSession: URLSession = .shared) {
        self.urlSession = urlSession
    }
    
    func dispatch<ReturnType: Codable>(request: URLRequest, decoder: JSONDecoder?) -> AnyPublisher<ReturnType, NetworkRequestError> {
        let decoder = decoder ?? JSONDecoder()
        
        return urlSession
            .dataTaskPublisher(for: request)
            .tryMap({ data, response in
                if let response = response as? HTTPURLResponse,
                   !(200...299).contains(response.statusCode) {
                    let networkRequestError = try CustomTimestampJSONDecoder().decode([String:APIError].self, from: data)
                    throw NetworkRequestError.apiError(networkRequestError["apiError"]!)
                }
                return data
            })
            .decode(type: ReturnType.self, decoder: decoder)
            .mapError { [self] error in
                handleError(error)
            }
            .receive(on: RunLoop.main)
            .eraseToAnyPublisher()
    }
    
    private func handleError(_ error: Error) -> NetworkRequestError {
        switch error {
        case let apiError as APIError:
            return .apiError(apiError)
        case is Swift.DecodingError:
            return .decodingError
        case let urlError as URLError:
            return .urlSessionFailed(urlError)
        case let error as NetworkRequestError:
            return error
        default:
            return .unknownError
        }
    }
}
