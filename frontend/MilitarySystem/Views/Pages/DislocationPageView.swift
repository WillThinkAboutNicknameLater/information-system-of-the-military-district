//
//  DislocationPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct DislocationPageView: View {
    @StateObject var viewModel = DislocationPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            DislocationTableView(viewModel: viewModel)
        }, listView: {
            DislocationListView(viewModel: viewModel)
        }, newEntityFormView: {
            DislocationFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
