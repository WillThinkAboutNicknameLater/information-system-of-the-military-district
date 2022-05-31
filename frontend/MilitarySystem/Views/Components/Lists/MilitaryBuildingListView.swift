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
        ScrollView {
            ForEach($viewModel.entityPage.content) { militaryBuilding in
                EditEntityCardView {
                    MilitaryBuildingView(militaryBuilding: militaryBuilding)
                } onSave: {
                    viewModel.updateEntity(militaryBuilding.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(militaryBuilding.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
