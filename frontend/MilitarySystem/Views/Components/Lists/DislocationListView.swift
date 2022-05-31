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
        ScrollView {
            ForEach($viewModel.entityPage.content) { dislocation in
                EditEntityCardView {
                    DislocationView(dislocation: dislocation)
                } onSave: {
                    viewModel.updateEntity(dislocation.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(dislocation.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
