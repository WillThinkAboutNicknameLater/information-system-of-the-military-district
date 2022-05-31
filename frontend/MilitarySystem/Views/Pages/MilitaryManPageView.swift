//
//  MilitaryManPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 16.05.2022.
//

import SwiftUI

struct MilitaryManPageView: View {
    @StateObject var viewModel = MilitaryManPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            MilitaryManTableView(viewModel: viewModel)
        }, listView: {
            MilitaryManListView(viewModel: viewModel)
        }, newEntityFormView: {
            MilitaryManFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
