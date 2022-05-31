//
//  MilitaryDistrictPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryDistrictPageView: View {
    @StateObject var viewModel = MilitaryDistrictPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            MilitaryDistrictTableView(viewModel: viewModel)
        }, listView: {
            MilitaryDistrictListView(viewModel: viewModel)
        }, newEntityFormView: {
            MilitaryDistrictFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}

