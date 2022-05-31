//
//  CustomDateJSONEncoder.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

class CustomDateJSONEncoder: JSONEncoder {
    override init() {
        super.init()
        self.dateEncodingStrategy = .formatted(CustomDateFormatter())
    }
}
