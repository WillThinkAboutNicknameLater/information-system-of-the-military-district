//
//  DislocationPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct DislocationPageView: View {
    @StateObject var viewModel = DislocationPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        DislocationTableView(viewModel: viewModel)
                    case .list:
                        DislocationListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
