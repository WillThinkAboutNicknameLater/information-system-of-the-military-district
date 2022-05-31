//
//  Entity.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

protocol Entity: Identifiable, Codable, FieldStringConvertible {
    init()
}
