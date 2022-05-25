//
//  StaffCategoryPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct StaffCategoryPageView: View {
    @StateObject var viewModel = StaffCategoryPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        StaffCategoryTableView(viewModel: viewModel)
                    case .list:
                        StaffCategoryListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}

