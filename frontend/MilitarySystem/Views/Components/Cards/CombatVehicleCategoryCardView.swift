//
//  CombatVehicleCategoryCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleCategoryCardView: View {
    @Binding var combatVehicleCategory: CombatVehicleCategory
    
    var onSave: (CombatVehicleCategory) -> Void
    
    var onDelete: (CombatVehicleCategory) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(combatVehicleCategory.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $combatVehicleCategory.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Группа:")
                        .bold()
                    TextField("", text: $combatVehicleCategory.combatVehicleGroupName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(combatVehicleCategory)
        } onDelete: {
            onDelete(combatVehicleCategory)
        }
    }
}
