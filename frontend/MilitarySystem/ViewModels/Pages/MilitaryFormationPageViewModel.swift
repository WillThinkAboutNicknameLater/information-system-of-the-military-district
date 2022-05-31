//
//  MilitaryFormationPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import Foundation

final class MilitaryFormationPageViewModel: EntityPageViewModel<MilitaryFormation> {
    init() {
        super.init(service: MilitaryFormationService(), entityFilter: MilitaryFormationFilter())
    }
}
