//
//  SubjectListView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct SubjectListView: View {
    @ObservedObject var viewModel: SubjectPageViewModel
    
    var body: some View {
        List($viewModel.entityPage.content) { subject in
            SubjectCardView(subject: subject, onSave: viewModel.updateEntity, onDelete: viewModel.deleteEntity)
        }
        .onAppear {
            viewModel.update()
        }
    }
}
