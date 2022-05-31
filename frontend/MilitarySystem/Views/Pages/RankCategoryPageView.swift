//
//  RankCategoryPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankCategoryPageView: View {
    @StateObject var viewModel = RankCategoryPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            RankCategoryTableView(viewModel: viewModel)
        }, listView: {
            RankCategoryListView(viewModel: viewModel)
        }, newEntityFormView: {
            RankCategoryFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}

