//
//  MilitaryBuildingPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class MilitaryBuildingPageViewModel: EntityPageViewModel<MilitaryBuilding> {
    init() {
        super.init(service: MilitaryBuildingService(), entityFilter: MilitaryBuildingFilter())
    }
}
