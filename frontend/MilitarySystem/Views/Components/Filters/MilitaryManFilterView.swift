//
//  MilitaryManFilterView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 16.05.2022.
//

import SwiftUI

struct MilitaryManFilterView: View {
    @ObservedObject var viewModel: MilitaryManPageViewModel
    
    var body: some View {
        VStack {
            PageFilterView(pageFilter: $viewModel.pageFilter)
            Spacer()
        }
        .frame(width: 240)
        .padding()
    }
}

