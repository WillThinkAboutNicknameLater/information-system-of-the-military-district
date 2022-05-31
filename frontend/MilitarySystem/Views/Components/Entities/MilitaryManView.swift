//
//  MilitaryManView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import SwiftUI

struct MilitaryManView: View {
    @Binding var militaryMan: MilitaryMan
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(MilitaryMan.getFieldName(for: .id)):")
                    .bold()
                Text("\(militaryMan.id)")
            }
            
            HStack {
                Text("\(MilitaryMan.getFieldName(for: .secondName)):")
                    .bold()
                TextField("", text: $militaryMan.secondName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryMan.getFieldName(for: .firstName)):")
                    .bold()
                TextField("", text: $militaryMan.firstName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryMan.getFieldName(for: .patronymic)):")
                    .bold()
                TextField("", text: $militaryMan.patronymic)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryMan.getFieldName(for: .dateOfBirth)):")
                    .bold()
                TextField("", value: $militaryMan.dateOfBirth, formatter: CustomDateFormatter())
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryMan.getFieldName(for: .identificationNumber)):")
                    .bold()
                TextField("", text: $militaryMan.identificationNumber)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryMan.getFieldName(for: .rankName)):")
                    .bold()
                TextField("", text: $militaryMan.rankName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryMan.getFieldName(for: .dateOfAward)):")
                    .bold()
                TextField("", value: $militaryMan.dateOfAward, formatter: CustomDateFormatter())
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
