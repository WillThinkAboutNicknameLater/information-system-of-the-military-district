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
        List($viewModel.entityPage.content) { combatVehicleGroup in
            CombatVehicleGroupCardView(combatVehicleGroup: combatVehicleGroup, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
