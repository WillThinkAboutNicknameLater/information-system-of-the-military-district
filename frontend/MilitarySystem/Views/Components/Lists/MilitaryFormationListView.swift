//
//  MilitaryFormationListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryFormationListView: View {
    @ObservedObject var viewModel: MilitaryFormationPageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { militaryFormation in
            MilitaryFormationCardView(militaryFormation: militaryFormation, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
