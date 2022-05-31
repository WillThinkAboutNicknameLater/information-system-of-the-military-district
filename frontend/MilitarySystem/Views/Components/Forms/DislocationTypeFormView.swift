//
//  DislocationTypeFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct DislocationTypeFormView: View {
    @ObservedObject var viewModel: DislocationTypePageViewModel
    
    var body: some View {
        EntityFormView(content: {
            DislocationTypeView(dislocationType: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
