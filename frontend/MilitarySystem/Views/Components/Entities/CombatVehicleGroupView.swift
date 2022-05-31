//
//  CombatVehicleGroupView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleGroupView: View {
    @Binding var combatVehicleGroup: CombatVehicleGroup
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(CombatVehicleGroup.getFieldName(for: .id)):")
                    .bold()
                Text("\(combatVehicleGroup.id)")
            }
            
            HStack {
                Text("\(CombatVehicleGroup.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $combatVehicleGroup.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
