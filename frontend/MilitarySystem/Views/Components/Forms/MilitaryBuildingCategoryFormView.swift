//
//  MilitaryBuildingCategoryFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct MilitaryBuildingCategoryFormView: View {
    @ObservedObject var viewModel: MilitaryBuildingCategoryPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            MilitaryBuildingCategoryView(militaryBuildingCategory: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
