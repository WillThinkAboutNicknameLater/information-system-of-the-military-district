//
//  ArmamentCategoryFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct ArmamentCategoryFormView: View {
    @ObservedObject var viewModel: ArmamentCategoryPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            ArmamentCategoryView(armamentCategory: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
