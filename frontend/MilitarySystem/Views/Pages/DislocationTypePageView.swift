//
//  DislocationTypePageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct DislocationTypePageView: View {
    @StateObject var viewModel = DislocationTypePageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            DislocationTypeTableView(viewModel: viewModel)
        }, listView: {
            DislocationTypeListView(viewModel: viewModel)
        }, newEntityFormView: {
            DislocationTypeFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
