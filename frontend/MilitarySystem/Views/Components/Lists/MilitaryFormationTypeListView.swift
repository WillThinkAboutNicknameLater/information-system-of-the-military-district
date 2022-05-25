//
//  MilitaryFormationTypeListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryFormationTypeListView: View {
    @ObservedObject var viewModel: MilitaryFormationTypePageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { militaryFormationType in
            MilitaryFormationTypeCardView(militaryFormationType: militaryFormationType, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
