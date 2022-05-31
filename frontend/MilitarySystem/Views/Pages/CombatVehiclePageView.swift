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
        EntityPageView(tableView: {
            CombatVehicleTableView(viewModel: viewModel)
        }, listView: {
            CombatVehicleListView(viewModel: viewModel)
        }, newEntityFormView: {
            CombatVehicleFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
