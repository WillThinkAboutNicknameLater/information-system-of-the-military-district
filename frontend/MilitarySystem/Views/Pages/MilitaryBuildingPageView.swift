//
//  MilitaryBuildingPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingPageView: View {
    @StateObject var viewModel = MilitaryBuildingPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            MilitaryBuildingTableView(viewModel: viewModel)
        }, listView: {
            MilitaryBuildingListView(viewModel: viewModel)
        }, newEntityFormView: {
            MilitaryBuildingFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
