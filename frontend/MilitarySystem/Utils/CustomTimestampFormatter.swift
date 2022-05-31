//
//  CustomTimestampFormatter.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import Foundation

final class CustomTimestampFormatter: DateFormatter {
    override init() {
        super.init()
        self.dateFormat = "dd-MM-yyyy HH:mm:ss"
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
