//
//  RankCategoryPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankCategoryPageView: View {
    @StateObject var viewModel = RankCategoryPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        RankCategoryTableView(viewModel: viewModel)
                    case .list:
                        RankCategoryListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}

