//
//  MilitaryBuildingCategoryCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingCategoryCardView: View {
    @Binding var militaryBuildingCategory: MilitaryBuildingCategory
    
    var onSave: (MilitaryBuildingCategory) -> Void
    
    var onDelete: (MilitaryBuildingCategory) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(militaryBuildingCategory.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $militaryBuildingCategory.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(militaryBuildingCategory)
        } onDelete: {
            onDelete(militaryBuildingCategory)
        }
    }
}
