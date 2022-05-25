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
        List($viewModel.entityPage.content) { armament in
            ArmamentCardView(armament: armament, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
