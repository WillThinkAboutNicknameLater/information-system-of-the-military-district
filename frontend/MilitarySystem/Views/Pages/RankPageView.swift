//
//  RankPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankPageView: View {
    @StateObject var viewModel = RankPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            RankTableView(viewModel: viewModel)
        }, listView: {
            RankListView(viewModel: viewModel)
        }, newEntityFormView: {
            RankFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
