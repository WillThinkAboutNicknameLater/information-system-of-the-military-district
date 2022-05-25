//
//  CombatVehicleCategoryListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleCategoryListView: View {
    @ObservedObject var viewModel: CombatVehicleCategoryPageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { combatVehicleCategory in
            CombatVehicleCategoryCardView(combatVehicleCategory: combatVehicleCategory, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
