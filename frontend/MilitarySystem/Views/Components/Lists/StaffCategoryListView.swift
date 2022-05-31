//
//  StaffCategoryListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct StaffCategoryListView: View {
    @ObservedObject var viewModel: StaffCategoryPageViewModel
    
    var body: some View {
        ScrollView {
            ForEach($viewModel.entityPage.content) { staffCategory in
                EditEntityCardView {
                    StaffCategoryView(staffCategory: staffCategory)
                } onSave: {
                    viewModel.updateEntity(staffCategory.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(staffCategory.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
