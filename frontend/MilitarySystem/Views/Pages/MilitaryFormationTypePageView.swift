//
//  MilitaryFormationTypePageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct MilitaryFormationTypePageView: View {
    @StateObject var viewModel = MilitaryFormationTypePageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            MilitaryFormationTypeTableView(viewModel: viewModel)
        }, listView: {
            MilitaryFormationTypeListView(viewModel: viewModel)
        }, newEntityFormView: {
            MilitaryFormationTypeFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
