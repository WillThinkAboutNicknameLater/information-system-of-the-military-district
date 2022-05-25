//
//  MilitaryDistrictPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryDistrictPageView: View {
    @StateObject var viewModel = MilitaryDistrictPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        MilitaryDistrictTableView(viewModel: viewModel)
                    case .list:
                        MilitaryDistrictListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}

