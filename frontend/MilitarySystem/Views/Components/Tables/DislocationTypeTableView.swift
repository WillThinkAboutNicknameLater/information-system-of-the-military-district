//
//  DislocationTypeTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct DislocationTypeTableView: View {
    @ObservedObject var viewModel: DislocationTypePageViewModel
    
    @State var sortOrder: [KeyPathComparator<DislocationType>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(DislocationType.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(DislocationType.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
