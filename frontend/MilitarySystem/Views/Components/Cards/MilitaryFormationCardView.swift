//
//  MilitaryFormationCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryFormationCardView: View {
    @Binding var militaryFormation: MilitaryFormation
    
    var onSave: (MilitaryFormation) -> Void
    
    var onDelete: (MilitaryFormation) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(militaryFormation.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $militaryFormation.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Тип:")
                        .bold()
                    TextField("", text: $militaryFormation.militaryFormationTypeName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Дата формирования:")
                        .bold()
                    TextField("", value: $militaryFormation.dateOfFormation, formatter: CustomDateFormatter())
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Идентификационный номер командира:")
                        .bold()
                    Text(militaryFormation.commander.identificationNumber)
                }
                
                HStack {
                    Text("Командир:")
                        .bold()
                    Text(militaryFormation.commander.getFullName())
                }
                
                HStack {
                    Text("ОКАТО:")
                        .bold()
                    Text(militaryFormation.dislocation.okato)
                }
                
                HStack {
                    Text("Дислокация:")
                        .bold()
                    TextField("", text: $militaryFormation.dislocation.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(militaryFormation)
        } onDelete: {
            onDelete(militaryFormation)
        }
    }
}
