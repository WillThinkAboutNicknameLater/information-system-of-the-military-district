//
//  ArmamentFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct ArmamentFormView: View {
    @ObservedObject var viewModel: ArmamentPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            ArmamentView(armament: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
