//
//  MilitarySpecialtyPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitarySpecialtyPageView: View {
    @StateObject var viewModel = MilitarySpecialtyPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        MilitarySpecialtyTableView(viewModel: viewModel)
                    case .list:
                        MilitarySpecialtyListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
