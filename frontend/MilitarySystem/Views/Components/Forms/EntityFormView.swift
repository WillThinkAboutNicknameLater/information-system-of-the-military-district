//
//  EntityFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct EntityFormView<T: Entity, Content: View>: View {
    @ViewBuilder var content: Content
    
    @ObservedObject var viewModel: EntityPageViewModel<T>
    
    var body: some View {
        VStack {
            NewEntityCardView {
                content
            } onSave: {
                viewModel.createEntity(viewModel.newEntity)
            }
            .padding()
            
            Spacer()
        }
    }
}
