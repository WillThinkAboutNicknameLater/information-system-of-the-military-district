//
//  ArmamentView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct ArmamentView: View {
    @Binding var armament: Armament
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(Armament.getFieldName(for: .id)):")
                    .bold()
                Text("\(armament.id)")
            }
            
            HStack {
                Text("\(Armament.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $armament.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(Armament.getFieldName(for: .armamentCategoryName)):")
                    .bold()
                TextField("", text: $armament.armamentCategoryName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(Armament.getFieldName(for: .serialNumber)):")
                    .bold()
                TextField("", text: $armament.serialNumber)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(Armament.getFieldName(for: .militaryFormationName)):")
                    .bold()
                TextField("", text: $armament.militaryFormationName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
