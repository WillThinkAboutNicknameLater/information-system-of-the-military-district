//
//  CombatVehicleGroupPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleGroupPageView: View {
    @StateObject var viewModel = CombatVehicleGroupPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        CombatVehicleGroupTableView(viewModel: viewModel)
                    case .list:
                        CombatVehicleGroupListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
