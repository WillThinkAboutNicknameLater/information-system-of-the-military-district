//
//  ArmamentCategoryService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import Foundation

final class ArmamentCategoryService: EntityService<ArmamentCategory> {
    init() {
        super.init(path: "/armament-categories")
    }
}
