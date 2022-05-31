//
//  ArmamentCategoryFilterView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 16.05.2022.
//

import SwiftUI

struct ArmamentCategoryFilterView: View {
    @ObservedObject var viewModel: ArmamentCategoryPageViewModel
    
    var body: some View {
        VStack {
            PageFilterView(pageFilter: $viewModel.pageFilter)
            Spacer()
        }
        .frame(width: 250)
        .padding()
    }
}
