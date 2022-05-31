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
            TableColumn("\(Armament.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(Armament.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
            TableColumn("\(Armament.getFieldName(for: .armamentCategoryName))", value: \.armamentCategoryName) {
                Text($0.armamentCategoryName)
            }
            TableColumn("\(Armament.getFieldName(for: .serialNumber))", value: \.serialNumber) {
                Text($0.serialNumber)
            }
            TableColumn("\(Armament.getFieldName(for: .militaryFormationName))", value: \.militaryFormationName) {
                Text($0.militaryFormationName)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
