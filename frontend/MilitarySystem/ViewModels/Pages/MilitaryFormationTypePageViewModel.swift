//
//  MilitaryFormationTypePageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import Foundation

final class MilitaryFormationTypePageViewModel: EntityPageViewModel<MilitaryFormationType> {
    init() {
        super.init(service: MilitaryFormationTypeService(), entityFilter: MilitaryFormationTypeFilter())
    }
}
