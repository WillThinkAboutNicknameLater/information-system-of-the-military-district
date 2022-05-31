//
//  NewEntityCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct NewEntityCardView<Content: View>: View {
    @ViewBuilder var content: Content
    
    var onSave: () -> Void
    
    var body: some View {
        HStack(alignment: .top) {
            content
            
            Spacer()
            
            Button(action: {
                onSave()
            }) {
                Image(systemName: "arrow.up.heart")
                    .imageScale(.large)
            }
            .buttonStyle(.plain)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding()
        .background(Color.gray.opacity(0.1), in: RoundedRectangle(cornerRadius: 10, style: .continuous))
        .padding(4)
    }
}
