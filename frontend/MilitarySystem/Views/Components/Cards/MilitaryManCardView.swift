//
//  MilitaryManCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import SwiftUI

struct MilitaryManCardView: View {
    @Binding var militaryMan: MilitaryMan
    
    var onSave: (MilitaryMan) -> Void
    
    var onDelete: (MilitaryMan) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(militaryMan.id)")
                }
                
                HStack {
                    Text("Фамилия:")
                        .bold()
                    TextField("", text: $militaryMan.secondName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Имя:")
                        .bold()
                    TextField("", text: $militaryMan.firstName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Отчество:")
                        .bold()
                    TextField("", text: $militaryMan.patronymic)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Дата рождения:")
                        .bold()
                    TextField("", value: $militaryMan.dateOfBirth, formatter: CustomDateFormatter())
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Идентификационный номер:")
                        .bold()
                    TextField("", text: $militaryMan.identificationNumber)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Звание:")
                        .bold()
                    TextField("", text: $militaryMan.rankName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Дата получения звания:")
                        .bold()
                    TextField("", value: $militaryMan.dateOfAward, formatter: CustomDateFormatter())
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(militaryMan)
        } onDelete: {
            onDelete(militaryMan)
        }
    }
}
