//
//  ArmamentPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct ArmamentPageView: View {
    @StateObject var viewModel = ArmamentPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            ArmamentTableView(viewModel: viewModel)
        }, listView: {
            ArmamentListView(viewModel: viewModel)
        }, newEntityFormView: {
            ArmamentFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
