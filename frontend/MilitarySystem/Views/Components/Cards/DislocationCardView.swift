//
//  DislocationCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct DislocationCardView: View {
    @Binding var dislocation: Dislocation
    
    var onSave: (Dislocation) -> Void
    
    var onDelete: (Dislocation) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(dislocation.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $dislocation.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Тип:")
                        .bold()
                    TextField("", text: $dislocation.dislocationTypeName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("Субъект:")
                        .bold()
                    TextField("", text: $dislocation.subjectName)
                        .textFieldStyle(PlainTextFieldStyle())
                }
                
                HStack {
                    Text("ОКАТО:")
                        .bold()
                    TextField("", text: $dislocation.okato)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(dislocation)
        } onDelete: {
            onDelete(dislocation)
        }
    }
}
