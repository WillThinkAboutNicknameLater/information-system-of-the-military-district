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
        ScrollView {
            ForEach($viewModel.entityPage.content) { militaryBuildingCategory in
                EditEntityCardView {
                    MilitaryBuildingCategoryView(militaryBuildingCategory: militaryBuildingCategory)
                } onSave: {
                    viewModel.updateEntity(militaryBuildingCategory.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(militaryBuildingCategory.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
