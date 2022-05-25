//
//  ArmamentCategoryPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 16.05.2022.
//

import SwiftUI

struct ArmamentCategoryPageView: View {
    @StateObject var viewModel = ArmamentCategoryPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        ArmamentCategoryTableView(viewModel: viewModel)
                    case .list:
                        ArmamentCategoryListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}

