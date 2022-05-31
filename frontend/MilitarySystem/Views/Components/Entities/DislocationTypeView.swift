//
//  DislocationTypeView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 25.05.2022.
//

import SwiftUI

struct DislocationTypeView: View {
    @Binding var dislocationType: DislocationType
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("\(DislocationType.getFieldName(for: .id)):")
                    .bold()
                Text("\(dislocationType.id)")
            }
            
            HStack {
                Text("\(DislocationType.getFieldName(for: .name)):")
                    .bold()
                TextField("", text: $dislocationType.name)
                    .textFieldStyle(PlainTextFieldStyle())
            }
        }
    }
}
