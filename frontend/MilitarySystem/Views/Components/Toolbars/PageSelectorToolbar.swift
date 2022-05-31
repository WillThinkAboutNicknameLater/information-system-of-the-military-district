//
//  PageSelectorToolbar.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct PageSelectorToolbar<T: Entity>: View {
    @ObservedObject var entityPageViewModel: EntityPageViewModel<T>
    
    var body: some View {
        HStack {
            Button(action: {
                entityPageViewModel.goToPreviousPage()
            }) {
                Image(systemName: "chevron.left")
            }
            .disabled(entityPageViewModel.isFirstPage())
            
            Button(action: {
                entityPageViewModel.goToNextPage()
            }) {
                Image(systemName: "chevron.right")
            }
            .disabled(entityPageViewModel.isLastPage())
        }
    }
}
