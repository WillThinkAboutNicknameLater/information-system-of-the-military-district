//
//  MilitarySpecialtyListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitarySpecialtyListView: View {
    @ObservedObject var viewModel: MilitarySpecialtyPageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { militarySpecialty in
            MilitarySpecialtyCardView(militarySpecialty: militarySpecialty, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
