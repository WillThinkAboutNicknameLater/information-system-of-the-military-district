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
        List($viewModel.entityPage.content) { rank in
            RankCardView(rank: rank, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
