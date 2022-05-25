//
//  RankCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct RankCardView: View {
    @Binding var rank: Rank
    
    var onSave: (Rank) -> Void
    
    var onDelete: (Rank) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(rank.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $rank.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Категория звания:")
                        .bold()
                    TextField("", text: $rank.rankCategoryName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Категория состава:")
                        .bold()
                    TextField("", text: $rank.staffCategoryName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(rank)
        } onDelete: {
            onDelete(rank)
        }
    }
}
