//
//  MilitaryBuildingCategoryService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class MilitaryBuildingCategoryService: EntityService<MilitaryBuildingCategory> {
    init() {
        super.init(path: "/military-building-categories")
    }
}
