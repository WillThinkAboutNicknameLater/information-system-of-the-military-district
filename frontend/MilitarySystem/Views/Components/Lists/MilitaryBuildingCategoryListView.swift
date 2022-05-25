//
//  MilitaryBuildingCategoryListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingCategoryListView: View {
    @ObservedObject var viewModel: MilitaryBuildingCategoryPageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { militaryBuildingCategory in
            MilitaryBuildingCategoryCardView(militaryBuildingCategory: militaryBuildingCategory, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
