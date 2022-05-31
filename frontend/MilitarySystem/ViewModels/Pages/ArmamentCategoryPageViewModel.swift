//
//  ArmamentCategoryPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import Foundation

final class ArmamentCategoryPageViewModel: EntityPageViewModel<ArmamentCategory> {
    init() {
        super.init(service: ArmamentCategoryService(), entityFilter: ArmamentCategoryFilter())
    }
}
