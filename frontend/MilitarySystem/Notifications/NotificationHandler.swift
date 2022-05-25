//
//  NotificationHandler.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 19.05.2022.
//

import Foundation
import UserNotifications

class NotificationHandler: NSObject, UNUserNotificationCenterDelegate {
    static let shared = NotificationHandler()
    
    func requestPermission(_ delegate : UNUserNotificationCenterDelegate? = nil, onDeny handler :  (()-> Void)? = nil){
        let center = UNUserNotificationCenter.current()
        center.getNotificationSettings(completionHandler: { settings in
            if settings.authorizationStatus == .denied {
                if let handler = handler {
                    handler()
                }
                return
            }
            
            if settings.authorizationStatus != .authorized  {
                center.requestAuthorization(options: [.alert, .sound, .badge]) { _ , error in
                    
                    if let error = error {
                        print(error.localizedDescription)
                    }
                }
            }
        })
        center.delegate = delegate ?? self
    }
    
    func addNotification(id: String, title: String, subtitle: String, sound: UNNotificationSound = UNNotificationSound.default, trigger: UNNotificationTrigger = UNTimeIntervalNotificationTrigger(timeInterval: 0.1, repeats: false)) {
        
        let content = UNMutableNotificationContent()
        content.title = title
        content.subtitle = subtitle
        content.sound = sound
        
        let request = UNNotificationRequest(identifier: id, content: content, trigger: trigger)
        UNUserNotificationCenter.current().add(request)
    }
    
    func addNotification(_ notificationContent: NotificationСontent) {
        addNotification(id: notificationContent.id, title: notificationContent.title, subtitle: notificationContent.subtitle, sound: notificationContent.sound, trigger: notificationContent.trigger)
    }
    
    func removeNotifications(_ ids : [String]) {
        UNUserNotificationCenter.current().removeDeliveredNotifications(withIdentifiers: ids)
        UNUserNotificationCenter.current().removePendingNotificationRequests(withIdentifiers: ids)
    }
    
}
