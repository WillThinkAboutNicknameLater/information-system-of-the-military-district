//
//  SimplifiedMilitaryMan.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

struct SimplifiedMilitaryMan: Codable, Equatable {
    var identificationNumber: String = ""
    
    var secondName: String = ""
    
    var firstName: String = ""
    
    var patronymic: String = ""
    
    func getFullName() -> String {
        return [secondName, firstName, patronymic].joined(separator: " ")
    }
}
