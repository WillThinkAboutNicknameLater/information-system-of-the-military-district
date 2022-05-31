//
//  DislocationService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import Foundation

final class DislocationService: EntityService<Dislocation> {
    init() {
        super.init(path: "/dislocations")
    }
}
