//
//  MilitaryManPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 16.05.2022.
//

import SwiftUI

struct MilitaryManPageView: View {
    @StateObject var viewModel = MilitaryManPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        MilitaryManTableView(viewModel: viewModel)
                    case .list:
                        MilitaryManListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
