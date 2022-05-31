//
//  RankCategoryTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankCategoryTableView: View {
    @ObservedObject var viewModel: RankCategoryPageViewModel
    
    @State var sortOrder: [KeyPathComparator<RankCategory>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(RankCategory.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(RankCategory.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
