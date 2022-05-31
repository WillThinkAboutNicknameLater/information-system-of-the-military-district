//
//  MilitarySpecialtyService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class MilitarySpecialtyService: EntityService<MilitarySpecialty> {
    init() {
        super.init(path: "/military-specialties")
    }
}
