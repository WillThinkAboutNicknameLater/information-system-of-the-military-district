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
        List($viewModel.entityPage.content) { staffCategory in
            StaffCategoryCardView(staffCategory: staffCategory, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
