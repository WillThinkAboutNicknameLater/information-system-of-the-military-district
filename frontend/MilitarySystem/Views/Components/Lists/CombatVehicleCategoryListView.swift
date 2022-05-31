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
        ScrollView {
            ForEach($viewModel.entityPage.content) { combatVehicleCategory in
                EditEntityCardView {
                    CombatVehicleCategoryView(combatVehicleCategory: combatVehicleCategory)
                } onSave: {
                    viewModel.updateEntity(combatVehicleCategory.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(combatVehicleCategory.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
