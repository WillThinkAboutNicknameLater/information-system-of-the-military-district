//
//  MilitaryManListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import SwiftUI

struct MilitaryManListView: View {
    @ObservedObject var viewModel: MilitaryManPageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { militaryMan in
            MilitaryManCardView(militaryMan: militaryMan, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
