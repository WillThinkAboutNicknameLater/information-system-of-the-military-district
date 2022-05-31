//
//  StaffCategoryTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct StaffCategoryTableView: View {
    @ObservedObject var viewModel: StaffCategoryPageViewModel
    
    @State var sortOrder: [KeyPathComparator<StaffCategory>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(StaffCategory.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(StaffCategory.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
