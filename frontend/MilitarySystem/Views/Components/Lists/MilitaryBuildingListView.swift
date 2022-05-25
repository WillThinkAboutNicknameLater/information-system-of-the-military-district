//
//  MilitaryBuildingListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingListView: View {
    @ObservedObject var viewModel: MilitaryBuildingPageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { militaryBuilding in
            MilitaryBuildingCardView(militaryBuilding: militaryBuilding, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
