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
        EntityPageView(tableView: {
            CombatVehicleCategoryTableView(viewModel: viewModel)
        }, listView: {
            CombatVehicleCategoryListView(viewModel: viewModel)
        }, newEntityFormView: {
            CombatVehicleCategoryFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
