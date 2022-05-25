//
//  ArmamentCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct ArmamentCardView: View {
    @Binding var armament: Armament
    
    var onSave: (Armament) -> Void
    
    var onDelete: (Armament) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(armament.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $armament.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Категория:")
                        .bold()
                    TextField("", text: $armament.armamentCategoryName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Серийный номер:")
                        .bold()
                    TextField("", text: $armament.serialNumber)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Воинское формирование:")
                        .bold()
                    TextField("", text: $armament.militaryFormationName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(armament)
        } onDelete: {
            onDelete(armament)
        }
    }
}
