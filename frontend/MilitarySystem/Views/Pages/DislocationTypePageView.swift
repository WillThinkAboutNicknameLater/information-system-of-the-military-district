//
//  DislocationTypePageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct DislocationTypePageView: View {
    @StateObject var viewModel = DislocationTypePageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        DislocationTypeTableView(viewModel: viewModel)
                    case .list:
                        DislocationTypeListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
