//
//  ArmamentCategoryPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 16.05.2022.
//

import SwiftUI

struct ArmamentCategoryPageView: View {
    @StateObject var viewModel = ArmamentCategoryPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            ArmamentCategoryTableView(viewModel: viewModel)
        }, listView: {
            ArmamentCategoryListView(viewModel: viewModel)
        }, newEntityFormView: {
            ArmamentCategoryFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}

