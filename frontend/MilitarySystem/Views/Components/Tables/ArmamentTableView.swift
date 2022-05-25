//
//  ArmamentTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct ArmamentTableView: View {
    @ObservedObject var viewModel: ArmamentPageViewModel
    
    @State var sortOrder: [KeyPathComparator<Armament>] = [
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
            TableColumn("Категория", value: \.armamentCategoryName) {
                Text($0.armamentCategoryName)
            }
            TableColumn("Серийный номер", value: \.serialNumber) {
                Text($0.serialNumber)
            }
            TableColumn("Воинское формирование", value: \.militaryFormationName) {
                Text($0.militaryFormationName)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
