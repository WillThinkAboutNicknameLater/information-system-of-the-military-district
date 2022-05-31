//
//  CombatVehicleGroupListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleGroupListView: View {
    @ObservedObject var viewModel: CombatVehicleGroupPageViewModel
    
    var body: some View {
        ScrollView {
            ForEach($viewModel.entityPage.content) { combatVehicleGroup in
                EditEntityCardView {
                    CombatVehicleGroupView(combatVehicleGroup: combatVehicleGroup)
                } onSave: {
                    viewModel.updateEntity(combatVehicleGroup.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(combatVehicleGroup.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
