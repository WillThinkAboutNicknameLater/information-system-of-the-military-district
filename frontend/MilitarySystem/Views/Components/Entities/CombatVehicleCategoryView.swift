//
//  CombatVehicleCategoryView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleCategoryView: View {
    @Binding var combatVehicleCategory: CombatVehicleCategory
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(CombatVehicleCategory.getFieldName(for: .id)):")
                    .bold()
                Text("\(combatVehicleCategory.id)")
            }
            
            HStack {
                Text("\(CombatVehicleCategory.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $combatVehicleCategory.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(CombatVehicleCategory.getFieldName(for: .combatVehicleGroupName)):")
                    .bold()
                TextField("", text: $combatVehicleCategory.combatVehicleGroupName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
