//
//  CombatVehicleGroupFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct CombatVehicleGroupFormView: View {
    @ObservedObject var viewModel: CombatVehicleGroupPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            CombatVehicleGroupView(combatVehicleGroup: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
