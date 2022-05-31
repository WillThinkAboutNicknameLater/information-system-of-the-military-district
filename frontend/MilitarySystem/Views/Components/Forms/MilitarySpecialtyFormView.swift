//
//  MilitarySpecialtyFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct MilitarySpecialtyFormView: View {
    @ObservedObject var viewModel: MilitarySpecialtyPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            MilitarySpecialtyView(militarySpecialty: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
