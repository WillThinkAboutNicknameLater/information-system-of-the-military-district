//
//  MilitaryBuildingCategoryView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingCategoryView: View {
    @Binding var militaryBuildingCategory: MilitaryBuildingCategory
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(MilitaryBuildingCategory.getFieldName(for: .id)):")
                    .bold()
                Text("\(militaryBuildingCategory.id)")
            }
            
            HStack {
                Text("\(MilitaryBuildingCategory.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $militaryBuildingCategory.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
