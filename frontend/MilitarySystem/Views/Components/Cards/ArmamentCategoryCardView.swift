//
//  ArmamentCategoryCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import SwiftUI

struct ArmamentCategoryCardView: View {
    @Binding var armamentCategory: ArmamentCategory
    
    var onSave: (ArmamentCategory) -> Void
    
    var onDelete: (ArmamentCategory) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(armamentCategory.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $armamentCategory.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(armamentCategory)
        } onDelete: {
            onDelete(armamentCategory)
        }
    }
}
