//
//  DislocationListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct DislocationListView: View {
    @ObservedObject var viewModel: DislocationPageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { dislocation in
            DislocationCardView(dislocation: dislocation, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
