//
//  SubjectFormView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 28.05.2022.
//

import SwiftUI

struct SubjectFormView: View {
    @ObservedObject var viewModel: SubjectPageViewModel
    
    var body: some View {
        EntityFormView(content: {
            SubjectView(subject: $viewModel.newEntity)
        }, viewModel: viewModel)
    }
}
