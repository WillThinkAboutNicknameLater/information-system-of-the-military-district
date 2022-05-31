//
//  CustomTimestampJSONDecoder.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import Foundation

class CustomTimestampJSONDecoder: JSONDecoder {
    override init() {
        super.init()
        self.dateDecodingStrategy = .formatted(CustomTimestampFormatter())
    }
}
