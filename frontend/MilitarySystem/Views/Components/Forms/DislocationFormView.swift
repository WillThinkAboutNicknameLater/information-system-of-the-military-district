//
//  DislocationFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct DislocationFormView: View {
    @ObservedObject var viewModel: DislocationPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            DislocationView(dislocation: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
