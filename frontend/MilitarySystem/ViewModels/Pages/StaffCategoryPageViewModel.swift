//
//  StaffCategoryPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class StaffCategoryPageViewModel: EntityPageViewModel<StaffCategory> {
    init() {
        super.init(service: StaffCategoryService(), entityFilter: StaffCategoryFilter())
    }
}
