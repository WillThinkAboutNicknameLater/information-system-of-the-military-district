//
//  RankCategoryListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankCategoryListView: View {
    @ObservedObject var viewModel: RankCategoryPageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { rankCategory in
            RankCategoryCardView(rankCategory: rankCategory, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
