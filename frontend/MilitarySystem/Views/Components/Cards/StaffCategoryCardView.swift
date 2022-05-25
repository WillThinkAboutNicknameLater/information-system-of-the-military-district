//
//  StaffCategoryCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct StaffCategoryCardView: View {
    @Binding var staffCategory: StaffCategory
    
    var onSave: (StaffCategory) -> Void
    
    var onDelete: (StaffCategory) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(staffCategory.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $staffCategory.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(staffCategory)
        } onDelete: {
            onDelete(staffCategory)
        }
    }
}
