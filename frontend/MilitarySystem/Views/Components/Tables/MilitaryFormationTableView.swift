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
            TableColumn("\(MilitaryFormation.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(MilitaryFormation.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
            TableColumn("\(MilitaryFormation.getFieldName(for: .militaryFormationTypeName))", value: \.militaryFormationTypeName) {
                Text($0.militaryFormationTypeName)
            }
            TableColumn("\(MilitaryFormation.getFieldName(for: .dateOfFormation))", value: \.dateOfFormation) {
                Text($0.dateOfFormation.formatted(date: .abbreviated, time: .omitted))
            }
            TableColumn("\(MilitaryFormation.getFieldName(for: .commanderIdentificationNumber))", value: \.commander.identificationNumber) {
                Text($0.commander.identificationNumber)
            }
            TableColumn("\(MilitaryFormation.getFieldName(for: .commanderFullName))", value: \.commander.secondName) {
                Text($0.commander.getFullName())
            }
            TableColumn("\(MilitaryFormation.getFieldName(for: .dislocationOkato))", value: \.dislocation.okato) {
                Text($0.dislocation.okato)
            }
            TableColumn("\(MilitaryFormation.getFieldName(for: .dislocationName))", value: \.dislocation.name) {
                Text($0.dislocation.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
