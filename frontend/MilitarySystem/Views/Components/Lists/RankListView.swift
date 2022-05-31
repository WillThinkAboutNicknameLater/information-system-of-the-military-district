//
//  RankListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankListView: View {
    @ObservedObject var viewModel: RankPageViewModel
    
    var body: some View {
        ScrollView {
            ForEach($viewModel.entityPage.content) { rank in
                EditEntityCardView {
                    RankView(rank: rank)
                } onSave: {
                    viewModel.updateEntity(rank.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(rank.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
