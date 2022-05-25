//
//  AppDelegate.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 19.05.2022.
//

import Foundation
import AppKit
import UserNotifications

class AppDelegate: NSObject, NSApplicationDelegate {
    func applicationDidFinishLaunching(_ notification: Notification) {
        UNUserNotificationCenter.current().delegate = self
    }
}

extension AppDelegate: UNUserNotificationCenterDelegate {
    func userNotificationCenter(_ center: UNUserNotificationCenter, didReceive response: UNNotificationResponse, withCompletionHandler completionHandler: @escaping () -> Void) {
        
        let notificationName = Notification.Name(response.notification.request.identifier)
        NotificationCenter.default.post(name: notificationName, object: response.notification.request.content)
        completionHandler()
    }
    
    func userNotificationCenter(_ center: UNUserNotificationCenter, willPresent notification: UNNotification, withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void) {
        let notificationName = Notification.Name(notification.request.identifier)
        NotificationCenter.default.post(name: notificationName, object: notification.request.content)
        completionHandler(.banner)
    }
}
