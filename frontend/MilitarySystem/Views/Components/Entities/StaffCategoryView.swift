//
//  StaffCategoryView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct StaffCategoryView: View {
    @Binding var staffCategory: StaffCategory
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(StaffCategory.getFieldName(for: .id)):")
                    .bold()
                Text("\(staffCategory.id)")
            }
            
            HStack {
                Text("\(StaffCategory.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $staffCategory.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
