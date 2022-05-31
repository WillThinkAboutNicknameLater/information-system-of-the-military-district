//
//  FieldStringConvertible.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import Foundation

protocol FieldStringConvertible {
    associatedtype Field: RawRepresentable where Field.RawValue: StringProtocol
    
    static func getFieldName(for field: Field) -> String
}

extension FieldStringConvertible {
    static func getFieldName(for field: Field) -> String {
        return field.rawValue.description
    }
}
