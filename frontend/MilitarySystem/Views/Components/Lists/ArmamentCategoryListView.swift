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
        ScrollView {
            ForEach($viewModel.entityPage.content) { armamentCategory in
                EditEntityCardView {
                    ArmamentCategoryView(armamentCategory: armamentCategory)
                } onSave: {
                    viewModel.updateEntity(armamentCategory.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(armamentCategory.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
