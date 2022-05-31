//
//  MilitarySpecialtyPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class MilitarySpecialtyPageViewModel: EntityPageViewModel<MilitarySpecialty> {
    init() {
        super.init(service: MilitarySpecialtyService(), entityFilter: MilitarySpecialtyFilter())
    }
}
