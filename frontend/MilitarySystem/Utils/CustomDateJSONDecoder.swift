//
//  CustomDateJSONDecoder.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import Foundation

class CustomDateJSONDecoder: JSONDecoder {
    override init() {
        super.init()
        self.dateDecodingStrategy = .formatted(CustomDateFormatter())
    }
}
