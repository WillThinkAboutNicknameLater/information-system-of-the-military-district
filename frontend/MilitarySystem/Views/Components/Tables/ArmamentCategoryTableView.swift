//
//  ArmamentCategoryTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import SwiftUI

struct ArmamentCategoryTableView: View {
    @ObservedObject var viewModel: ArmamentCategoryPageViewModel
    
    @State var sortOrder: [KeyPathComparator<ArmamentCategory>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(ArmamentCategory.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(ArmamentCategory.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
