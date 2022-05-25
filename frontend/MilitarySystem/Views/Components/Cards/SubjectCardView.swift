//
//  SubjectCardView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct SubjectCardView: View {
    @Binding var subject: Subject
    
    var onSave: (Subject) -> Void
    
    var onDelete: (Subject) -> Void
    
    var body: some View {
        CardView {
            VStack(alignment: .leading, spacing: 8) {
                HStack {
                    Text("ID:")
                        .bold()
                    Text("\(subject.id)")
                }
                
                HStack {
                    Text("Название:")
                        .bold()
                    TextField("", text: $subject.name)
                        .textFieldStyle(PlainTextFieldStyle())
                }
            }
        } onSave: {
            onSave(subject)
        } onDelete: {
            onDelete(subject)
        }
    }
}
