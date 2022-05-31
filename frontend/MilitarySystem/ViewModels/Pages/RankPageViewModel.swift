//
//  RankPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class RankPageViewModel: EntityPageViewModel<Rank> {
    init() {
        super.init(service: RankService(), entityFilter: RankFilter())
    }
}
