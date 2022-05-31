//
//  MilitarySpecialtyPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitarySpecialtyPageView: View {
    @StateObject var viewModel = MilitarySpecialtyPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(tableView: {
            MilitarySpecialtyTableView(viewModel: viewModel)
        }, listView: {
            MilitarySpecialtyListView(viewModel: viewModel)
        }, newEntityFormView: {
            MilitarySpecialtyFormView(viewModel: viewModel)
        }, viewModel: viewModel)
    }
}
