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
        ScrollView {
            ForEach($viewModel.entityPage.content) { dislocationType in
                EditEntityCardView {
                    DislocationTypeView(dislocationType: dislocationType)
                } onSave: {
                    viewModel.updateEntity(dislocationType.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(dislocationType.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
