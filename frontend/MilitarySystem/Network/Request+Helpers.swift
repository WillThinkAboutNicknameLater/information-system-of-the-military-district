//
//  Request+Helpers.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 17.05.2022.
//

import Foundation

extension Request {
    var method: HTTPMethod { return .get }
    
    var contentType: HTTPContentType { return .json }
    
    var queryParams: [String: Any]? { return nil }
    
    var body: [String: Any]? { return nil }
    
    var headers: [String: String]? { return nil }
    
    var decoder: JSONDecoder? { return JSONDecoder() }
    
    private func requestBodyFrom(params: [String: Any]?) -> Data? {
        guard let params = params else { return nil }
        guard let httpBody = try? JSONSerialization.data(withJSONObject: params, options: []) else {
            return nil
        }
        return httpBody
    }
    
    private func queryItemsFrom(params: [String: Any]?) -> [URLQueryItem]? {
        guard let params = params else { return nil }
        var result: [URLQueryItem] = []
        params.forEach { param in
            if let array = param.value as? Array<Any> {
                array.forEach { v in
                    result.append(URLQueryItem(name: param.key, value: String(describing: v)))
                }
            } else {
                result.append(URLQueryItem(name: param.key, value: String(describing: param.value)))
            }
        }
        return result
    }
    
    func asURLRequest(baseURL: String) -> URLRequest? {
        guard var urlComponents = URLComponents(string: baseURL) else { return nil }
        urlComponents.path = "\(urlComponents.path)\(path)"
        urlComponents.queryItems = queryItemsFrom(params: queryParams)
        
        guard let finalURL = urlComponents.url else { return nil }
        var request = URLRequest(url: finalURL)
        request.httpMethod = method.rawValue
        request.httpBody = requestBodyFrom(params: body)
        let defaultHeaders: [String: String] = [
            HTTPHeaderField.contentType.rawValue: contentType.rawValue,
            HTTPHeaderField.acceptType.rawValue: contentType.rawValue
        ]
        request.allHTTPHeaderFields = defaultHeaders.merging(headers ?? [:], uniquingKeysWith: { (first, _) in first })
        return request
    }
}
