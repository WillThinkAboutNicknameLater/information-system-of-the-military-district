//
//  MilitaryBuildingCategoryPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingCategoryPageView: View {
    @StateObject var viewModel = MilitaryBuildingCategoryPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        MilitaryBuildingCategoryTableView(viewModel: viewModel)
                    case .list:
                        MilitaryBuildingCategoryListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
