//
//  MilitaryFormationPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct MilitaryFormationPageView: View {
    @StateObject var viewModel = MilitaryFormationPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        MilitaryFormationTableView(viewModel: viewModel)
                    case .list:
                        MilitaryFormationListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
