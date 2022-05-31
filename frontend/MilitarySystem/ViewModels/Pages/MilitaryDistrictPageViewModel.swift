//
//  MilitaryDistrictPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class MilitaryDistrictPageViewModel: EntityPageViewModel<MilitaryDistrict> {
    init() {
        super.init(service: MilitaryDistrictService(), entityFilter: MilitaryDistrictFilter())
    }
}
