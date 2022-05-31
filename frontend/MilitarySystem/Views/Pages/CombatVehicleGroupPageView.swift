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
        EntityPageView(tableView: {
            CombatVehicleGroupTableView(viewModel: viewModel)
        }, listView: {
            CombatVehicleGroupListView(viewModel: viewModel)
        }, newEntityFormView: {
            CombatVehicleGroupFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
