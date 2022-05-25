//
//  CombatVehicleCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleCardView: View {
    @Binding var combatVehicle: CombatVehicle
    
    var onSave: (CombatVehicle) -> Void
    
    var onDelete: (CombatVehicle) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(combatVehicle.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $combatVehicle.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Категория:")
                        .bold()
                    TextField("", text: $combatVehicle.combatVehicleCategoryName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Серийный номер:")
                        .bold()
                    TextField("", text: $combatVehicle.serialNumber)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Воинское формирование:")
                        .bold()
                    TextField("", text: $combatVehicle.militaryFormationName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(combatVehicle)
        } onDelete: {
            onDelete(combatVehicle)
        }
    }
}
