//
//  RankService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class RankService: EntityService<Rank> {
    init() {
        super.init(path: "/ranks")
    }
}
