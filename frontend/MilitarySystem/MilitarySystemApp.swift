//
//  MilitarySystemApp.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 12.05.2022.
//

import SwiftUI
import AppKit

@main
struct MilitarySystemApp: App {
    @NSApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .frame(minWidth: 1000, minHeight: 600)
        }
        .commands {
            SidebarCommands()
            ThemePickerCommands()
        }
    }
}
