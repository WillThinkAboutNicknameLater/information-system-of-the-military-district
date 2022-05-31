//
//  CombatVehicleCategoryFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct CombatVehicleCategoryFormView: View {
    @ObservedObject var viewModel: CombatVehicleCategoryPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            CombatVehicleCategoryView(combatVehicleCategory: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
