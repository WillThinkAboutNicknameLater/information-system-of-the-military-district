//
//  SubjectService.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 19.05.2022.
//

import Foundation

final class SubjectService: EntityService<Subject> {
    init() {
        super.init(path: "/subjects")
    }
}
