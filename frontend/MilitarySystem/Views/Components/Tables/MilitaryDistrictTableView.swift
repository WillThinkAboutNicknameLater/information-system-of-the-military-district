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
            TableColumn("ID", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("Название", value: \.name) {
                Text($0.name)
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
            TableColumn("ОКАТО", value: \.headquartersDislocation.okato) {
                Text($0.headquartersDislocation.okato)
            }
            TableColumn("Дислокация", value: \.headquartersDislocation.name) {
                Text($0.headquartersDislocation.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
