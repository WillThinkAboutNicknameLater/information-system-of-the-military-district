//
//  RankCategoryCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankCategoryCardView: View {
    @Binding var rankCategory: RankCategory
    
    var onSave: (RankCategory) -> Void
    
    var onDelete: (RankCategory) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(rankCategory.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $rankCategory.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(rankCategory)
        } onDelete: {
            onDelete(rankCategory)
        }
    }
}
