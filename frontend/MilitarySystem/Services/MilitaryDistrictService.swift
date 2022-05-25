//
//  MilitaryDistrictService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class MilitaryDistrictService: EntityService<MilitaryDistrict> {
    init() {
        super.init(path: "/military-districts", decoder: CustomDateJSONDecoder())
    }
}
