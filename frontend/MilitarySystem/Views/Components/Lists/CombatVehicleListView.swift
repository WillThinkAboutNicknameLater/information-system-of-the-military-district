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
        ScrollView {
            ForEach($viewModel.entityPage.content) { combatVehicle in
                EditEntityCardView {
                    CombatVehicleView(combatVehicle: combatVehicle)
                } onSave: {
                    viewModel.updateEntity(combatVehicle.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(combatVehicle.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
