//
//  MilitaryFormationTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct MilitaryFormationTableView: View {
    @ObservedObject var viewModel: MilitaryFormationPageViewModel
    
    @State var sortOrder: [KeyPathComparator<MilitaryFormation>] = [
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
            TableColumn("Тип", value: \.militaryFormationTypeName) {
                Text($0.militaryFormationTypeName)
            }
            TableColumn("Дата формирования", value: \.dateOfFormation) {
                Text($0.dateOfFormation.formatted(date: .abbreviated, time: .omitted))
            }
            TableColumn("Идентификационный номер командира", value: \.commander.identificationNumber) {
                Text($0.commander.identificationNumber)
            }
            TableColumn("Командир", value: \.commander.secondName) {
                Text($0.commander.getFullName())
            }
            TableColumn("ОКАТО", value: \.dislocation.okato) {
                Text($0.dislocation.okato)
            }
            TableColumn("Дислокация", value: \.dislocation.name) {
                Text($0.dislocation.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
