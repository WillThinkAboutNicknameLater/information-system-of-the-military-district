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
        ScrollView {
            ForEach($viewModel.entityPage.content) { militaryFormation in
                EditEntityCardView {
                    MilitaryFormationView(militaryFormation: militaryFormation)
                } onSave: {
                    viewModel.updateEntity(militaryFormation.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(militaryFormation.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
