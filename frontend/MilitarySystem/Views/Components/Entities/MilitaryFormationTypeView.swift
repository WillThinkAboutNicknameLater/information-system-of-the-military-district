//
//  MilitaryFormationTypeView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryFormationTypeView: View {
    @Binding var militaryFormationType: MilitaryFormationType
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(MilitaryFormationType.getFieldName(for: .id)):")
                    .bold()
                Text("\(militaryFormationType.id)")
            }
            
            HStack {
                Text("\(MilitaryFormationType.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $militaryFormationType.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
