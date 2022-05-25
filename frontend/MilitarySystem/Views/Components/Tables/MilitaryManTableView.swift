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
            TableColumn("ID", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("Фамилия", value: \.secondName) {
                Text(String($0.secondName))
            }
            TableColumn("Имя", value: \.firstName) {
                Text(String($0.firstName))
            }
            TableColumn("Отчество", value: \.patronymic) {
                Text(String($0.patronymic))
            }
            TableColumn("Дата рождения", value: \.dateOfBirth) {
                Text($0.dateOfBirth.formatted(date: .abbreviated, time: .omitted))
            }
            TableColumn("Идентификационный номер", value: \.identificationNumber) {
                Text(String($0.identificationNumber))
            }
            TableColumn("Звание", value: \.rankName) {
                Text(String($0.rankName))
            }
            TableColumn("Дата получения звания", value: \.dateOfAward) {
                Text($0.dateOfAward.formatted(date: .abbreviated, time: .omitted))
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}

