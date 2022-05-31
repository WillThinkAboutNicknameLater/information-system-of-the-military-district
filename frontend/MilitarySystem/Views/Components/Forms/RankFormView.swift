//
//  RankFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct RankFormView: View {
    @ObservedObject var viewModel: RankPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            RankView(rank: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
