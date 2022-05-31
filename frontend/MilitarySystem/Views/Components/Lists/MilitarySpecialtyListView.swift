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
        ScrollView {
            ForEach($viewModel.entityPage.content) { militarySpecialty in
                EditEntityCardView {
                    MilitarySpecialtyView(militarySpecialty: militarySpecialty)
                } onSave: {
                    viewModel.updateEntity(militarySpecialty.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(militarySpecialty.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
