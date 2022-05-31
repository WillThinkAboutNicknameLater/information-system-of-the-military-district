//
//  RankCategoryView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankCategoryView: View {
    @Binding var rankCategory: RankCategory
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(RankCategory.getFieldName(for: .id)):")
                    .bold()
                Text("\(rankCategory.id)")
            }
            
            HStack {
                Text("\(RankCategory.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $rankCategory.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
