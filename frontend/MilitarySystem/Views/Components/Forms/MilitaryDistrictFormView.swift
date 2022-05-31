//
//  MilitaryDistrictFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct MilitaryDistrictFormView: View {
    @ObservedObject var viewModel: MilitaryDistrictPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            MilitaryDistrictView(militaryDistrict: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
