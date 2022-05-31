//
//  Optional+boundString.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import Foundation

extension Optional where Wrapped == String {
    var _boundString: String? {
        get {
            return self
        }
        set {
            self = newValue
        }
    }
    
    public var boundString: String {
        get {
            return _boundString ?? ""
        }
        set {
            _boundString = newValue.isEmpty ? nil : newValue
        }
    }
}
