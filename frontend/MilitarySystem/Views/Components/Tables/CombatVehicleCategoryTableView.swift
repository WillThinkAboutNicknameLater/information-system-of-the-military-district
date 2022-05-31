//
//  CombatVehicleCategoryTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct CombatVehicleCategoryTableView: View {
    @ObservedObject var viewModel: CombatVehicleCategoryPageViewModel
    
    @State var sortOrder: [KeyPathComparator<CombatVehicleCategory>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(CombatVehicleCategory.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(CombatVehicleCategory.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
            TableColumn("\(CombatVehicleCategory.getFieldName(for: .combatVehicleGroupName))", value: \.combatVehicleGroupName) {
                Text($0.combatVehicleGroupName)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
