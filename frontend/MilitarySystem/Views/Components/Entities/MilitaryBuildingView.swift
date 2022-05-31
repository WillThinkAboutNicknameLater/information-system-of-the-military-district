//
//  MilitaryBuildingView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct MilitaryBuildingView: View {
    @Binding var militaryBuilding: MilitaryBuilding
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(MilitaryBuilding.getFieldName(for: .id)):")
                    .bold()
                Text("\(militaryBuilding.id)")
            }
            
            HStack {
                Text("\(MilitaryBuilding.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $militaryBuilding.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryBuilding.getFieldName(for: .militaryBuildingCategoryName)):")
                    .bold()
                TextField("", text: $militaryBuilding.militaryBuildingCategoryName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(MilitaryBuilding.getFieldName(for: .militaryFormationName)):")
                    .bold()
                TextField("", text: $militaryBuilding.militaryFormationName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
