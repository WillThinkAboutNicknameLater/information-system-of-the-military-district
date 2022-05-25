//
//  MilitaryBuildingPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingPageView: View {
    @StateObject var viewModel = MilitaryBuildingPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        MilitaryBuildingTableView(viewModel: viewModel)
                    case .list:
                        MilitaryBuildingListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
