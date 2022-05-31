//
//  SubjectPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 19.05.2022.
//

import Foundation

final class SubjectPageViewModel: EntityPageViewModel<Subject> {
    init() {
        super.init(service: SubjectService(), entityFilter: SubjectFilter())
    }
}
