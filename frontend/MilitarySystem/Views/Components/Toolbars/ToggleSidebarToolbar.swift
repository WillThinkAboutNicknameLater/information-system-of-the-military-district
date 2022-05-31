//
//  ToggleSidebarToolbar.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 18.05.2022.
//

import SwiftUI

struct ToggleSidebarToolbar: View {
    var body: some View {
        Button {
            toggleSidebar()
        } label: {
            Image(systemName: "sidebar.leading")
                .imageScale(.large)
        }
    }
    
    private func toggleSidebar() {
        NSApp.keyWindow?.firstResponder?.tryToPerform(#selector(NSSplitViewController.toggleSidebar(_:)), with: nil)
    }
}
