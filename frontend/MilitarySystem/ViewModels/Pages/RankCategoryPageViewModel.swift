//
//  RankCategoryPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class RankCategoryPageViewModel: EntityPageViewModel<RankCategory> {
    init() {
        super.init(service: RankCategoryService(), entityFilter: RankCategoryFilter())
    }
}
