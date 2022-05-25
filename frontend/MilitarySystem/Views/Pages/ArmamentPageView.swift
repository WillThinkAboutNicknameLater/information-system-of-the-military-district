//
//  ArmamentPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct ArmamentPageView: View {
    @StateObject var viewModel = ArmamentPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        ArmamentTableView(viewModel: viewModel)
                    case .list:
                        ArmamentListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
