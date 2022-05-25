//
//  DislocationTypeListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct DislocationTypeListView: View {
    @ObservedObject var viewModel: DislocationTypePageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { dislocationType in
            DislocationTypeCardView(dislocationType: dislocationType, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
