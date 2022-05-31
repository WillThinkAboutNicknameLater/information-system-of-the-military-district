//
//  MilitaryDistrictListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryDistrictListView: View {
    @ObservedObject var viewModel: MilitaryDistrictPageViewModel
    
    var body: some View {
        ScrollView {
            ForEach($viewModel.entityPage.content) { militaryDistrict in
                EditEntityCardView {
                    MilitaryDistrictView(militaryDistrict: militaryDistrict)
                } onSave: {
                    viewModel.updateEntity(militaryDistrict.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(militaryDistrict.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
