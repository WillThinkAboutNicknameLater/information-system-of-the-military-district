//
//  CombatVehicleFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct CombatVehicleFormView: View {
    @ObservedObject var viewModel: CombatVehiclePageViewModel
    
    var body: some View {
        EntityFormView(content: {
            CombatVehicleView(combatVehicle: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
