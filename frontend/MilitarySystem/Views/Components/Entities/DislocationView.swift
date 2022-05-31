//
//  DislocationView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct DislocationView: View {
    @Binding var dislocation: Dislocation
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(Dislocation.getFieldName(for: .id)):")
                    .bold()
                Text("\(dislocation.id)")
            }
            
            HStack {
                Text("\(Dislocation.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $dislocation.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(Dislocation.getFieldName(for: .dislocationTypeName)):")
                    .bold()
                TextField("", text: $dislocation.dislocationTypeName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(Dislocation.getFieldName(for: .subjectName)):")
                    .bold()
                TextField("", text: $dislocation.subjectName)
                    .textFieldStyle(PlainTextFieldStyle())
            }
            
            HStack {
                Text("\(Dislocation.getFieldName(for: .okato)):")
                    .bold()
                TextField("", text: $dislocation.okato)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
