//
//  MilitaryFormationTypePageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct MilitaryFormationTypePageView: View {
    @StateObject var viewModel = MilitaryFormationTypePageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        MilitaryFormationTypeTableView(viewModel: viewModel)
                    case .list:
                        MilitaryFormationTypeListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
