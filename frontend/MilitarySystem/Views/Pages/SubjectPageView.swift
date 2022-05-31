//
//  SubjectPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 19.05.2022.
//

import SwiftUI

struct SubjectPageView: View {
    @StateObject var viewModel = SubjectPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            SubjectTableView(viewModel: viewModel)
        }, listView: {
            SubjectListView(viewModel: viewModel)
        }, newEntityFormView: {
            SubjectFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
