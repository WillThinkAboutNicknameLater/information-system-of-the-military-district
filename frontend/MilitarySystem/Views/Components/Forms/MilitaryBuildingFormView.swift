//
//  MilitaryBuildingFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct MilitaryBuildingFormView: View {
    @ObservedObject var viewModel: MilitaryBuildingPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            MilitaryBuildingView(militaryBuilding: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
