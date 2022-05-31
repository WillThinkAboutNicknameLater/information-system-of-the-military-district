//
//  MilitaryFormationView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryFormationView: View {
    @Binding var militaryFormation: MilitaryFormation
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(MilitaryFormation.getFieldName(for: .id)):")
                    .bold()
                Text("\(militaryFormation.id)")
            }
            
            HStack {
                Text("\(MilitaryFormation.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $militaryFormation.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryFormation.getFieldName(for: .militaryFormationTypeName)):")
                    .bold()
                TextField("", text: $militaryFormation.militaryFormationTypeName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryFormation.getFieldName(for: .dateOfFormation)):")
                    .bold()
                TextField("", value: $militaryFormation.dateOfFormation, formatter: CustomDateFormatter())
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryFormation.getFieldName(for: .commanderIdentificationNumber)):")
                    .bold()
                Text(militaryFormation.commander.identificationNumber)
            }
            
            HStack {
                Text("\(MilitaryFormation.getFieldName(for: .commanderFullName)):")
                    .bold()
                Text(militaryFormation.commander.getFullName())
            }
            
            HStack {
                Text("\(MilitaryFormation.getFieldName(for: .dislocationOkato)):")
                    .bold()
                Text(militaryFormation.dislocation.okato)
            }
            
            HStack {
                Text("\(MilitaryFormation.getFieldName(for: .dislocationName)):")
                    .bold()
                TextField("", text: $militaryFormation.dislocation.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
