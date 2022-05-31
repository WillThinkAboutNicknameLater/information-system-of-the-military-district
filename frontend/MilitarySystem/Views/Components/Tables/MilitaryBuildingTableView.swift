//
//  MilitaryBuildingTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingTableView: View {
    @ObservedObject var viewModel: MilitaryBuildingPageViewModel
    
    @State var sortOrder: [KeyPathComparator<MilitaryBuilding>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(MilitaryBuilding.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(MilitaryBuilding.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
            TableColumn("\(MilitaryBuilding.getFieldName(for: .militaryBuildingCategoryName))", value: \.militaryBuildingCategoryName) {
                Text($0.militaryBuildingCategoryName)
            }
            TableColumn("\(MilitaryBuilding.getFieldName(for: .militaryFormationName))", value: \.militaryFormationName) {
                Text($0.militaryFormationName)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
