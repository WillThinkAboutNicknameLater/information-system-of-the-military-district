//
//  DisplayModePicker.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import SwiftUI

struct DisplayModePicker: View {
    @Binding var mode: ViewMode

    var body: some View {
        Picker("Display Mode", selection: $mode) {
            ForEach(ViewMode.allCases) { viewMode in
                viewMode.label
            }
        }
        .pickerStyle(SegmentedPickerStyle())
    }
}
