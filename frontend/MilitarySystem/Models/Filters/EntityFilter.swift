//
//  EntityFilter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 24.05.2022.
//

import Foundation

protocol EntityFilter: Codable {
    var searchName: String? { get set }
    
    init()
}
