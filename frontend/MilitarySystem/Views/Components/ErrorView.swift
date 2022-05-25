//
//  ErrorView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 19.05.2022.
//

import SwiftUI

struct ErrorView: View {
    var title: String
    
    var error: NetworkRequestError
    
    var onClose: () -> Void
    
    var titleView: some View {
        Text(title)
            .font(.system(size: 16, weight: .semibold))
            .foregroundColor(.red)
            .multilineTextAlignment(.leading)
    }
    
    var closeButton: some View {
        Button(action: {
            withAnimation(.easeIn) {
                onClose()
            }
        }, label: {
            Image(systemName: "xmark.circle")
                .foregroundColor(.red)
        })
    }
    
    var description: some View {
        Text(error.getMessage())
            .font(.system(size: 12))
            .foregroundColor(.red)
    }
    
    var body: some View {
        VStack(spacing: 10) {
            HStack {
                titleView
                Spacer()
                closeButton
            }
            .padding([.top, .leading, .trailing])
            
            HStack {
                description
                Spacer()
            }
            .padding([.leading, .bottom, .trailing])
        }
        .frame(maxWidth: .infinity)
        .background(Color(red: 252 / 255, green: 236 / 255, blue: 234 / 255, opacity: 1))
        .cornerRadius(15)
        .padding(.horizontal)
    }
}
