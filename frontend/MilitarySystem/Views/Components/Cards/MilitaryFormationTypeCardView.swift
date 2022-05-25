//
//  MilitaryFormationTypeCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryFormationTypeCardView: View {
    @Binding var militaryFormationType: MilitaryFormationType
    
    var onSave: (MilitaryFormationType) -> Void
    
    var onDelete: (MilitaryFormationType) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(militaryFormationType.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $militaryFormationType.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(militaryFormationType)
        } onDelete: {
            onDelete(militaryFormationType)
        }
    }
}
