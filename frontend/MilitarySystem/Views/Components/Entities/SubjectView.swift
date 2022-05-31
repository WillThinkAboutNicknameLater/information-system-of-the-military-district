//
//  SubjectView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct SubjectView: View {
    @Binding var subject: Subject
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(Subject.getFieldName(for: .id)):")
                    .bold()
                Text("\(subject.id)")
            }
            
            HStack {
                Text("\(Subject.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $subject.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
