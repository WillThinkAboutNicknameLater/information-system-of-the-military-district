//
//  MilitaryFormationFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct MilitaryFormationFormView: View {
    @ObservedObject var viewModel: MilitaryFormationPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            MilitaryFormationView(militaryFormation: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
