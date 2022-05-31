//
//  DislocationTypePageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import Foundation

final class DislocationTypePageViewModel: EntityPageViewModel<DislocationType> {
    init() {
        super.init(service: DislocationTypeService(), entityFilter: DislocationTypeFilter())
    }
}
