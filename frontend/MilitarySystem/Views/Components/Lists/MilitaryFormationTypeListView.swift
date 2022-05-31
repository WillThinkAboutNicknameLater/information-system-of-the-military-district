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
        ScrollView {
            ForEach($viewModel.entityPage.content) { militaryFormationType in
                EditEntityCardView {
                    MilitaryFormationTypeView(militaryFormationType: militaryFormationType)
                } onSave: {
                    viewModel.updateEntity(militaryFormationType.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(militaryFormationType.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
