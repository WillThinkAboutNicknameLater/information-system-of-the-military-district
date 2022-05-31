//
//  MilitaryBuildingCategoryPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class MilitaryBuildingCategoryPageViewModel: EntityPageViewModel<MilitaryBuildingCategory> {
    init() {
        super.init(service: MilitaryBuildingCategoryService(), entityFilter: MilitaryBuildingCategoryFilter())
    }
}
