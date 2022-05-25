//
//  CombatVehiclePageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehiclePageView: View {
    @StateObject var viewModel = CombatVehiclePageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        CombatVehicleTableView(viewModel: viewModel)
                    case .list:
                        CombatVehicleListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
