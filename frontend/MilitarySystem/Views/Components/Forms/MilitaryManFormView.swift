//
//  MilitaryManFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct MilitaryManFormView: View {
    @ObservedObject var viewModel: MilitaryManPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            MilitaryManView(militaryMan: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
