//
//  Optional+asString.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 29.05.2022.
//

import Foundation

extension Optional {
    public var asString: String {
        get {
            return self != nil ? "\(self!)" : "-"
        }
    }
}
