//
//  ArmamentService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class ArmamentService: EntityService<Armament> {
    init() {
        super.init(path: "/armaments")
    }
}
