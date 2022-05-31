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
        ScrollView {
            ForEach($viewModel.entityPage.content) { rankCategory in
                EditEntityCardView {
                    RankCategoryView(rankCategory: rankCategory)
                } onSave: {
                    viewModel.updateEntity(rankCategory.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(rankCategory.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
