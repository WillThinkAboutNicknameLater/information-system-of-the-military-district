//
//  StaffCategoryPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct StaffCategoryPageView: View {
    @StateObject var viewModel = StaffCategoryPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            StaffCategoryTableView(viewModel: viewModel)
        }, listView: {
            StaffCategoryListView(viewModel: viewModel)
        }, newEntityFormView: {
            StaffCategoryFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}

