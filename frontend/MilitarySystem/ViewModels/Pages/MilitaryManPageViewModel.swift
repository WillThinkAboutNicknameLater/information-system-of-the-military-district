//
//  MilitaryManPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 16.05.2022.
//

import Foundation

final class MilitaryManPageViewModel: EntityPageViewModel<MilitaryMan> {
    init() {
        super.init(service: MilitaryManService(), entityFilter: MilitaryManFilter())
    }
}
