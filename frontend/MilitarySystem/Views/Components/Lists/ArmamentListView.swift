//
//  ArmamentListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct ArmamentListView: View {
    @ObservedObject var viewModel: ArmamentPageViewModel
    
    var body: some View {
        ScrollView {
            ForEach($viewModel.entityPage.content) { armament in
                EditEntityCardView {
                    ArmamentView(armament: armament)
                } onSave: {
                    viewModel.updateEntity(armament.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(armament.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
