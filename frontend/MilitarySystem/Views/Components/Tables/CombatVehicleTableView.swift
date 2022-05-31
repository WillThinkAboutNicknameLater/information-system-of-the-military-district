//
//  CombatVehicleTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleTableView: View {
    @ObservedObject var viewModel: CombatVehiclePageViewModel
    
    @State var sortOrder: [KeyPathComparator<CombatVehicle>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(CombatVehicle.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(CombatVehicle.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
            TableColumn("\(CombatVehicle.getFieldName(for: .combatVehicleCategoryName))", value: \.combatVehicleCategoryName) {
                Text($0.combatVehicleCategoryName)
            }
            TableColumn("\(CombatVehicle.getFieldName(for: .serialNumber))", value: \.serialNumber) {
                Text($0.serialNumber)
            }
            TableColumn("\(CombatVehicle.getFieldName(for: .militaryFormationName))", value: \.militaryFormationName) {
                Text($0.militaryFormationName)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
