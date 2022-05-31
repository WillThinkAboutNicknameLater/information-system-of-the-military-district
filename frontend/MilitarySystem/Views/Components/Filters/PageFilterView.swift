//
//  PageFilterView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct PageFilterView: View {
    @Binding var pageFilter: PageFilter
    
    var body: some View {
        Form {
            TextField("Номер страницы", value: $pageFilter.pageNumber, format: .number)
                .textFieldStyle(.plain)
            
            TextField("Размер страницы", value: $pageFilter.pageSize, format: .number)
                .textFieldStyle(.roundedBorder)
            
            
//            Picker("Направление сортировки", selection: $pageFilter.sortDirection) {
//                Text("По возрастанию").tag(SortOrder.forward)
//                Text("По убыванию").tag(SortOrder.reverse)
//            }.pickerStyle(.radioGroup)
        }
    }
}
