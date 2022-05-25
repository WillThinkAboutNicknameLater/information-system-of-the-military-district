//
//  CombatVehicleListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleListView: View {
    @ObservedObject var viewModel: CombatVehiclePageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { combatVehicle in
            CombatVehicleCardView(combatVehicle: combatVehicle, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
