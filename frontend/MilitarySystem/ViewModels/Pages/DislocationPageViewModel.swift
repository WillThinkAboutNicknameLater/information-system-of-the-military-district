//
//  DislocationPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import Foundation

final class DislocationPageViewModel: EntityPageViewModel<Dislocation> {
    init() {
        super.init(service: DislocationService(), entityFilter: DislocationFilter())
    }
}
