//
//  ReloadToolbar.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct ReloadToolbar: View {
    @Binding var isLoading: Bool
    
    var onReload: () -> Void
    
    var body: some View {
        HStack {
            if (isLoading) {
                ProgressView()
                    .scaleEffect(0.5)
            }
            
            Button(action: onReload) {
                Image(systemName: "arrow.triangle.2.circlepath.circle")
            }
        }
    }
}
