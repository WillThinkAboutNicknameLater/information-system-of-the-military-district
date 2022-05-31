//
//  CombatVehicleView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleView: View {
    @Binding var combatVehicle: CombatVehicle
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(CombatVehicle.getFieldName(for: .id)):")
                    .bold()
                Text("\(combatVehicle.id)")
            }
            
            HStack {
                Text("\(CombatVehicle.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $combatVehicle.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(CombatVehicle.getFieldName(for: .combatVehicleCategoryName)):")
                    .bold()
                TextField("", text: $combatVehicle.combatVehicleCategoryName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(CombatVehicle.getFieldName(for: .serialNumber)):")
                    .bold()
                TextField("", text: $combatVehicle.serialNumber)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(CombatVehicle.getFieldName(for: .militaryFormationName)):")
                    .bold()
                TextField("", text: $combatVehicle.militaryFormationName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
