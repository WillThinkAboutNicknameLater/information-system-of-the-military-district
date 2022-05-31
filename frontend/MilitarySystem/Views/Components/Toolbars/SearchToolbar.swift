//
//  SearchToolbar.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 29.05.2022.
//

import SwiftUI

struct SearchToolbar: View {
    @Binding var searchText: String
    
    var body: some View {
        HStack {
            Image(systemName: "magnifyingglass")
                .foregroundColor(.gray)
            TextField("Поиск", text: $searchText)
                .textFieldStyle(PlainTextFieldStyle())
                .frame(width: 200)
            //.multilineTextAlignment(.leading)
            //.lineLimit(1)
            //.truncationMode(.tail)
        }
        .padding(8)
        //.background(Color.gray.opacity(0.1))
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(.separator, lineWidth: 1)
        )
    }
}

