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
        ScrollView {
            ForEach($viewModel.entityPage.content) { subject in
                EditEntityCardView {
                    SubjectView(subject: subject)
                } onSave: {
                    viewModel.updateEntity(subject.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(subject.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
