//
//  MilitarySpecialtyView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitarySpecialtyView: View {
    @Binding var militarySpecialty: MilitarySpecialty
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(MilitarySpecialty.getFieldName(for: .id)):")
                    .bold()
                Text("\(militarySpecialty.id)")
            }
            
            HStack {
                Text("\(MilitarySpecialty.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $militarySpecialty.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
