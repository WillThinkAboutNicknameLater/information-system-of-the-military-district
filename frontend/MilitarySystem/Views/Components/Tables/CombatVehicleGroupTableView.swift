//
//  CombatVehicleGroupTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleGroupTableView: View {
    @ObservedObject var viewModel: CombatVehicleGroupPageViewModel
    
    @State var sortOrder: [KeyPathComparator<CombatVehicleGroup>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(CombatVehicleGroup.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(CombatVehicleGroup.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
