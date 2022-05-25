//
//  ArmamentCategoryListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import SwiftUI

struct ArmamentCategoryListView: View {
    @ObservedObject var viewModel: ArmamentCategoryPageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { armamentCategory in
            ArmamentCategoryCardView(armamentCategory: armamentCategory, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
