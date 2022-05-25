//
//  DislocationTypeCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct DislocationTypeCardView: View {
    @Binding var dislocationType: DislocationType
    
    var onSave: (DislocationType) -> Void
    
    var onDelete: (DislocationType) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(dislocationType.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $dislocationType.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(dislocationType)
        } onDelete: {
            onDelete(dislocationType)
        }
    }
}
