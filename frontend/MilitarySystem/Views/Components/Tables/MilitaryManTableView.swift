//
//  MilitaryManTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 16.05.2022.
//

import SwiftUI

struct MilitaryManTableView: View {
    @ObservedObject var viewModel: MilitaryManPageViewModel
    
    @State var sortOrder: [KeyPathComparator<MilitaryMan>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(MilitaryMan.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(MilitaryMan.getFieldName(for: .secondName))", value: \.secondName) {
                Text(String($0.secondName))
            }
            TableColumn("\(MilitaryMan.getFieldName(for: .firstName))", value: \.firstName) {
                Text(String($0.firstName))
            }
            TableColumn("\(MilitaryMan.getFieldName(for: .patronymic))", value: \.patronymic) {
                Text(String($0.patronymic))
            }
            TableColumn("\(MilitaryMan.getFieldName(for: .dateOfBirth))", value: \.dateOfBirth) {
                Text($0.dateOfBirth.formatted(date: .abbreviated, time: .omitted))
            }
            TableColumn("\(MilitaryMan.getFieldName(for: .identificationNumber))", value: \.identificationNumber) {
                Text(String($0.identificationNumber))
            }
            TableColumn("\(MilitaryMan.getFieldName(for: .rankName))", value: \.rankName) {
                Text(String($0.rankName))
            }
            TableColumn("\(MilitaryMan.getFieldName(for: .dateOfAward))", value: \.dateOfAward) {
                Text($0.dateOfAward.formatted(date: .abbreviated, time: .omitted))
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}

