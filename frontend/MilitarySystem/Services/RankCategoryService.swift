//
//  RankCategoryService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class RankCategoryService: EntityService<RankCategory> {
    init() {
        super.init(path: "/rank-categories")
    }
}
