//
//  StaffCategoryService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class StaffCategoryService: EntityService<StaffCategory> {
    init() {
        super.init(path: "/staff-categories")
    }
}
