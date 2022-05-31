//
//  ArmamentPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class ArmamentPageViewModel: EntityPageViewModel<Armament> {
    init() {
        super.init(service: ArmamentService(), entityFilter: ArmamentFilter())
    }
}
