//
//  ViewMode.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation
import SwiftUI

enum ViewMode: String, CaseIterable, Identifiable {
    case table
    case list
    case newEntityForm
    
    var id: Self { self }
    
    var labelContent: (name: String, systemImage: String) {
        switch self {
        case .table:
            return ("Table", "tablecells")
        case .list:
            return ("List", "list.bullet")
        case .newEntityForm:
            return ("New entity form", "plus")
        }
    }

    var label: some View {
        let content = labelContent
        return Label(content.name, systemImage: content.systemImage)
    }
}
