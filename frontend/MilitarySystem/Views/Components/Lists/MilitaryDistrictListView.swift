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
        List($viewModel.entityPage.content) { militaryDistrict in
            MilitaryDistrictCardView(militaryDistrict: militaryDistrict, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
