//
//  StaffCategoryFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct StaffCategoryFormView: View {
    @ObservedObject var viewModel: StaffCategoryPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            StaffCategoryView(staffCategory: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
