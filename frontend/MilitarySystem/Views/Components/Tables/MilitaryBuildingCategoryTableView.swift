//
//  MilitaryBuildingCategoryTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingCategoryTableView: View {
    @ObservedObject var viewModel: MilitaryBuildingCategoryPageViewModel
    
    @State var sortOrder: [KeyPathComparator<MilitaryBuildingCategory>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(MilitaryBuildingCategory.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(MilitaryBuildingCategory.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
