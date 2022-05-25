//
//  MilitaryBuildingCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingCardView: View {
    @Binding var militaryBuilding: MilitaryBuilding
    
    var onSave: (MilitaryBuilding) -> Void
    
    var onDelete: (MilitaryBuilding) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(militaryBuilding.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $militaryBuilding.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Категория:")
                        .bold()
                    TextField("", text: $militaryBuilding.militaryBuildingCategoryName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Воинское формирование:")
                        .bold()
                    TextField("", text: $militaryBuilding.militaryFormationName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(militaryBuilding)
        } onDelete: {
            onDelete(militaryBuilding)
        }
    }
}
