//
//  MilitaryFormationTypeFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct MilitaryFormationTypeFormView: View {
    @ObservedObject var viewModel: MilitaryFormationTypePageViewModel
    
    var body: some View {
        EntityFormView(content: {
            MilitaryFormationTypeView(militaryFormationType: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
