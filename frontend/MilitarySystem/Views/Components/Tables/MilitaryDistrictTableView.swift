//
//  MilitaryDistrictTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryDistrictTableView: View {
    @ObservedObject var viewModel: MilitaryDistrictPageViewModel
    
    @State var sortOrder: [KeyPathComparator<MilitaryDistrict>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(MilitaryDistrict.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(MilitaryDistrict.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
            TableColumn("\(MilitaryDistrict.getFieldName(for: .dateOfFormation))", value: \.dateOfFormation) {
                Text($0.dateOfFormation.formatted(date: .abbreviated, time: .omitted))
            }
            TableColumn("\(MilitaryDistrict.getFieldName(for: .commanderIdentificationNumber))", value: \.commander.identificationNumber) {
                Text($0.commander.identificationNumber)
            }
            TableColumn("\(MilitaryDistrict.getFieldName(for: .commanderFullName))", value: \.commander.secondName) {
                Text($0.commander.getFullName())
            }
            TableColumn("\(MilitaryDistrict.getFieldName(for: .headquartersDislocationOkato))", value: \.headquartersDislocation.okato) {
                Text($0.headquartersDislocation.okato)
            }
            TableColumn("\(MilitaryDistrict.getFieldName(for: .headquartersDislocationName))", value: \.headquartersDislocation.name) {
                Text($0.headquartersDislocation.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
