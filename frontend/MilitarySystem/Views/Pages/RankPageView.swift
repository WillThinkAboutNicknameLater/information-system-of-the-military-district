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
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        RankTableView(viewModel: viewModel)
                    case .list:
                        RankListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
