//
//  DislocationTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct DislocationTableView: View {
    @ObservedObject var viewModel: DislocationPageViewModel
    
    @State var sortOrder: [KeyPathComparator<Dislocation>] = [
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
            TableColumn("Тип", value: \.dislocationTypeName) {
                Text($0.dislocationTypeName)
            }
            TableColumn("Субъект", value: \.subjectName) {
                Text($0.subjectName)
            }
            TableColumn("ОКАТО", value: \.okato) {
                Text($0.okato)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}

