//
//  SubjectTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 19.05.2022.
//

import SwiftUI

struct SubjectTableView: View {
    @ObservedObject var viewModel: SubjectPageViewModel
    
    @State var sortOrder: [KeyPathComparator<Subject>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("ID", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("Название", value: \.name) {
                Text($0.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
