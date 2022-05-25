//
//  MilitarySpecialtyCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitarySpecialtyCardView: View {
    @Binding var militarySpecialty: MilitarySpecialty
    
    var onSave: (MilitarySpecialty) -> Void
    
    var onDelete: (MilitarySpecialty) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(militarySpecialty.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $militarySpecialty.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(militarySpecialty)
        } onDelete: {
            onDelete(militarySpecialty)
        }
    }
}
