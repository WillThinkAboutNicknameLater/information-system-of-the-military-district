//
//  ArmamentCategoryView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import SwiftUI

struct ArmamentCategoryView: View {
    @Binding var armamentCategory: ArmamentCategory
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(ArmamentCategory.getFieldName(for: .id)):")
                    .bold()
                Text("\(armamentCategory.id)")
            }
            
            HStack {
                Text("\(ArmamentCategory.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $armamentCategory.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
