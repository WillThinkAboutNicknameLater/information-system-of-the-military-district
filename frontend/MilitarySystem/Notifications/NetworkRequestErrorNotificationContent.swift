//
//  NetworkRequestErrorNotificationContent.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation

class NetworkRequestErrorNotificationContent: NotificationСontent {
    init(networkRequestError: NetworkRequestError) {
        super.init(title: networkRequestError.getTitle(), subtitle: networkRequestError.getMessage())
    }
}
