//
//  NetworkRequestError.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 17.05.2022.
//

import Foundation

enum NetworkRequestError: Error {
    case apiError(_ error: APIError)
    case decodingError
    case urlSessionFailed(_ error: URLError)
    case unknownError
    
    func getTitle() -> String {
        switch self {
        case .apiError(let error):
            return error.title
        case .decodingError:
            return "Ошибка в декодировании"
        case .urlSessionFailed(_):
            return "Ошибка сессии"
        case .unknownError:
            return "Что-то пошло не так"
        }
    }
    
    func getMessage() -> String {
        switch self {
        case .apiError(let error):
            return error.message
        case .decodingError:
            return "Не удалось декодировать."
        case .urlSessionFailed(let error):
            return error.localizedDescription
        case .unknownError:
            return "Мы работаем над этой проблемой."
        }
    }
}
