//
//  CombatVehicleCategoryPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleCategoryPageView: View {
    @StateObject var viewModel = CombatVehicleCategoryPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        CombatVehicleCategoryTableView(viewModel: viewModel)
                    case .list:
                        CombatVehicleCategoryListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
