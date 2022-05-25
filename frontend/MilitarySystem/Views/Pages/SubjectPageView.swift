//
//  SubjectPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 19.05.2022.
//

import SwiftUI

struct SubjectPageView: View {
    @StateObject var viewModel = SubjectPageViewModel()
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    var body: some View {
        EntityPageView(content: {
            HStack(spacing: 0) {
                Group {
                    switch mode {
                    case .table:
                        SubjectTableView(viewModel: viewModel)
                    case .list:
                        SubjectListView(viewModel: viewModel)
                    }
                }
            }
        }, viewModel: viewModel)
    }
}
