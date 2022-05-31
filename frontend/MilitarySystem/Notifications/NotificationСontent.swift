//
//  NotificationСontent.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation
import UserNotifications

class NotificationСontent: Identifiable {
    var id: String
    
    var title: String
    
    var subtitle: String
    
    var sound: UNNotificationSound

    var trigger: UNNotificationTrigger
    
    init(id: String = UUID().uuidString, title: String, subtitle: String, sound: UNNotificationSound = .default, trigger: UNNotificationTrigger = UNTimeIntervalNotificationTrigger(timeInterval: 0.1, repeats: false)) {
        self.id = id
        self.title = title
        self.subtitle = subtitle
        self.sound = sound
        self.trigger = trigger
    }
}
