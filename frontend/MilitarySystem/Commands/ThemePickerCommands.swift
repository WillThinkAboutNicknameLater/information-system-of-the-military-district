//
//  ThemePickerCommands.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 20.05.2022.
//

import Foundation
import SwiftUI

struct ThemePickerCommands: Commands {
    
    enum AppearanceTheme: String {
        case system
        case light
        case dark
    }
    
    @AppStorage("appearanceTheme") private var appearanceTheme: AppearanceTheme = .system
    
    var body: some Commands {
        CommandGroup(before: .sidebar) {
            Picker(selection: $appearanceTheme, label: Text("Оформление")) {
                Text("Примененное к системе")
                    .tag(AppearanceTheme.system)
                
                Text("Светлое")
                    .tag(AppearanceTheme.light)
                
                Text("Темное")
                    .tag(AppearanceTheme.dark)
            }
            .onChange(of: appearanceTheme) { _ in
                updateAppTheme(theme: appearanceTheme)
            }
            .onAppear {
                updateAppTheme(theme: appearanceTheme)
            }
            
            Divider()
        }
    }
    
    private func updateAppTheme(theme: AppearanceTheme) {
        switch appearanceTheme {
        case .system:
            NSApp.appearance = nil
        case .light:
            NSApp.appearance = NSAppearance(named: .aqua)
        case .dark:
            NSApp.appearance = NSAppearance(named: .darkAqua)
        }
    }
}
