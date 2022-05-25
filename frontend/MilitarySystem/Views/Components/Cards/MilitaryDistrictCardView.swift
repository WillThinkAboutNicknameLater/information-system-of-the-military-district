//
//  MilitaryDistrictCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryDistrictCardView: View {
    @Binding var militaryDistrict: MilitaryDistrict
    
    var onSave: (MilitaryDistrict) -> Void
    
    var onDelete: (MilitaryDistrict) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(militaryDistrict.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $militaryDistrict.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Дата формирования:")
                        .bold()
                    TextField("", value: $militaryDistrict.dateOfFormation, formatter: CustomDateFormatter())
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Идентификационный номер командира:")
                        .bold()
                    Text(militaryDistrict.commander.identificationNumber)
                }
                
                HStack {
                    Text("Командир:")
                        .bold()
                    Text(militaryDistrict.commander.getFullName())
                }
                
                HStack {
                    Text("ОКАТО:")
                        .bold()
                    Text(militaryDistrict.headquartersDislocation.okato)
                }
                
                HStack {
                    Text("Штаб:")
                        .bold()
                    TextField("", text: $militaryDistrict.headquartersDislocation.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(militaryDistrict)
        } onDelete: {
            onDelete(militaryDistrict)
        }
    }
}
