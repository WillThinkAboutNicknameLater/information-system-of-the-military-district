//
//  MilitarySpecialtyTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitarySpecialtyTableView: View {
    @ObservedObject var viewModel: MilitarySpecialtyPageViewModel
    
    @State var sortOrder: [KeyPathComparator<MilitarySpecialty>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(MilitarySpecialty.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(MilitarySpecialty.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
