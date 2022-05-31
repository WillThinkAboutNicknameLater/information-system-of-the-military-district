//
//  CustomDateFormatter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import Foundation

final class CustomDateFormatter: DateFormatter {
    override init() {
        super.init()
        self.dateFormat = "dd-MM-yyyy"
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
