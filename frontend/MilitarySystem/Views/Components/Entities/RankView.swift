//
//  RankView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankView: View {
    @Binding var rank: Rank
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(Rank.getFieldName(for: .id)):")
                    .bold()
                Text("\(rank.id)")
            }
            
            HStack {
                Text("\(Rank.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $rank.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(Rank.getFieldName(for: .rankCategoryName)):")
                    .bold()
                TextField("", text: $rank.rankCategoryName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(Rank.getFieldName(for: .staffCategoryName)):")
                    .bold()
                TextField("", text: $rank.staffCategoryName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
