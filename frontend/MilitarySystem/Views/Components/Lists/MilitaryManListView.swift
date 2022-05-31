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
        ScrollView {
            ForEach($viewModel.entityPage.content) { militaryMan in
                EditEntityCardView {
                    MilitaryManView(militaryMan: militaryMan)
                } onSave: {
                    viewModel.updateEntity(militaryMan.wrappedValue)
                } onDelete: {
                    viewModel.deleteEntity(militaryMan.wrappedValue)
                }
            }
            .padding()
        }
        .onAppear {
            viewModel.update()
        }
    }
}
