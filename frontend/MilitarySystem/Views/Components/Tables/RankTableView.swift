//
//  RankTableView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankTableView: View {
    @ObservedObject var viewModel: RankPageViewModel
    
    @State var sortOrder: [KeyPathComparator<Rank>] = [
        .init(\.id, order: SortOrder.forward)
    ]
    
    var body: some View {
        Table(viewModel.entityPage.content.sorted(using: sortOrder), selection: $viewModel.selectedEntities, sortOrder: $sortOrder) {
            TableColumn("\(Rank.getFieldName(for: .id))", value: \.id) {
                Text(String($0.id))
            }
            TableColumn("\(Rank.getFieldName(for: .name))", value: \.name) {
                Text($0.name)
            }
            TableColumn("\(Rank.getFieldName(for: .rankCategoryName))", value: \.rankCategoryName) {
                Text($0.rankCategoryName)
            }
            TableColumn("\(Rank.getFieldName(for: .staffCategoryName))", value: \.staffCategoryName) {
                Text($0.staffCategoryName)
            }
        }
        .onAppear {
            viewModel.update()
        }
    }
}
