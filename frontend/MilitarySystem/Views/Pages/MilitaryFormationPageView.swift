//
//  MilitaryFormationPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct MilitaryFormationPageView: View {
    @StateObject var viewModel = MilitaryFormationPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            MilitaryFormationTableView(viewModel: viewModel)
        }, listView: {
            MilitaryFormationListView(viewModel: viewModel)
        }, newEntityFormView: {
            MilitaryFormationFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
