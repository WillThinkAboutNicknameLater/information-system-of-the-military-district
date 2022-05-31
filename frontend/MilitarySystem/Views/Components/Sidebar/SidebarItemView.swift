//
//  SidebarItemView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import SwiftUI

struct SidebarItemView<Destination: View>: View {
    var id = UUID()
    
    var title: String
    
    var imageName: String
    
    @Binding var selectedItem: UUID?
    
    @ViewBuilder var destinationView: Destination
    
    var body: some View {
        NavigationLink(tag: id, selection: $selectedItem) {
            destinationView
                .navigationTitle(title)
        } label: {
            Label(title, systemImage: imageName)
        }
    }
}
