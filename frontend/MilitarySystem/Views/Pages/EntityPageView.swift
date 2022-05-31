//
//  EntityPageView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct EntityPageView<T: Entity, TableView: View, ListView: View, NewEntityFormView: View>: View {
    @ViewBuilder var tableView: TableView
    
    @ViewBuilder var listView: ListView
    
    @ViewBuilder var newEntityFormView: NewEntityFormView
    
    @SceneStorage("viewMode") private var mode: ViewMode = .table
    
    @ObservedObject var viewModel: EntityPageViewModel<T>
    
    var body: some View {
        HStack(spacing: 0) {
            Group {
                switch mode {
                case .table:
                    tableView
                case .list:
                    listView
                case .newEntityForm:
                    newEntityFormView
                }
            }
        }
        .navigationSubtitle("Записей: \(viewModel.getTotalElements()). Страница: \(viewModel.getCurrentPageNumber()) из \(viewModel.getTotalPages())")
        .searchable(text: $viewModel.searchText)
        .toolbar {
            ToolbarItem {
                ReloadToolbar(isLoading: $viewModel.isLoading, action: viewModel.update)
            }
            ToolbarItem {
                DisplayModePicker(mode: $mode)
            }
            ToolbarItem {
                PageSelectorToolbar(entityPageViewModel: viewModel)
            }
        }
    }
}
