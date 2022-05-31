//
//  MilitaryDistrictView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryDistrictView: View {
    @Binding var militaryDistrict: MilitaryDistrict
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(MilitaryDistrict.getFieldName(for: .id)):")
                    .bold()
                Text("\(militaryDistrict.id)")
            }
            
            HStack {
                Text("\(MilitaryDistrict.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $militaryDistrict.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryDistrict.getFieldName(for: .dateOfFormation)):")
                    .bold()
                TextField("", value: $militaryDistrict.dateOfFormation, formatter: CustomDateFormatter())
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryDistrict.getFieldName(for: .commanderIdentificationNumber)):")
                    .bold()
                Text(militaryDistrict.commander.identificationNumber)
            }
            
            HStack {
                Text("\(MilitaryDistrict.getFieldName(for: .commanderFullName)):")
                    .bold()
                Text(militaryDistrict.commander.getFullName())
            }
            
            HStack {
                Text("\(MilitaryDistrict.getFieldName(for: .headquartersDislocationOkato)):")
                    .bold()
                Text(militaryDistrict.headquartersDislocation.okato)
            }
            
            HStack {
                Text("\(MilitaryDistrict.getFieldName(for: .headquartersDislocationName)):")
                    .bold()
                TextField("", text: $militaryDistrict.headquartersDislocation.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
