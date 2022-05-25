//
//  DislocationTypeService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

final class DislocationTypeService: EntityService<DislocationType> {
    init() {
        super.init(path: "/dislocation-types")
    }
}
