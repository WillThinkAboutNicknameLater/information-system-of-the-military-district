//
//  CombatVehicleGroupCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleGroupCardView: View {
    @Binding var combatVehicleGroup: CombatVehicleGroup
    
    var onSave: (CombatVehicleGroup) -> Void
    
    var onDelete: (CombatVehicleGroup) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(combatVehicleGroup.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $combatVehicleGroup.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(combatVehicleGroup)
        } onDelete: {
            onDelete(combatVehicleGroup)
        }
    }
}
