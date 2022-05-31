//
//  RankCategoryFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct RankCategoryFormView: View {
    @ObservedObject var viewModel: RankCategoryPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            RankCategoryView(rankCategory: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
