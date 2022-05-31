//
//  MilitaryBuildingCategoryPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingCategoryPageView: View {
    @StateObject var viewModel = MilitaryBuildingCategoryPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            MilitaryBuildingCategoryTableView(viewModel: viewModel)
        }, listView: {
            MilitaryBuildingCategoryListView(viewModel: viewModel)
        }, newEntityFormView: {
            MilitaryBuildingCategoryFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
