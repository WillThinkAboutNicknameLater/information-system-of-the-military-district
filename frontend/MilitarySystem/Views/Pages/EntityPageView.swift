//
//  EntityPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct EntityPageView<T: Entity, Content: View>: View {
    @ViewBuilder var content: Content
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    @ObservedObject var viewModel: EntityPageViewModel<T>
    
    var body: some View {
        content
            .toolbar {
                ToolbarItem {
                    ReloadToolbar(isLoading: $viewModel.isLoading, onReload: viewModel.update)
                }
                ToolbarItem(placement: .principal) {
                    Spacer()
                }
                ToolbarItem {
                    DisplayModePicker(mode: $mode)
                }
                ToolbarItem {
                    Spacer()
                }
                ToolbarItem {
                    PageSelectorToolbar(entityPageViewModel: viewModel)
                }
            }
            .navigationSubtitle("Записей: \(viewModel.getTotalElements()). Страница: \(viewModel.getCurrentPageNumber()) из \(viewModel.getTotalPages())")
    }
}
