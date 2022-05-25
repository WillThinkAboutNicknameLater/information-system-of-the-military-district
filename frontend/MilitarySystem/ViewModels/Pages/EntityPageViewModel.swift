//
//  EntityPageViewModel.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 23.05.2022.
//

import Foundation
import Combine

class EntityPageViewModel<T: Entity>: ObservableObject {
    private var cancellables: [AnyCancellable] = []

    private let service: EntityService<T>

    @Published var entityPage: EntityPage<T> = EntityPage<T>()

    @Published var selectedEntities = Set<T.ID>()

    @Published var entityFilter: EntityFilter

    @Published var pageFilter = PageFilter()

    @Published var isLoading = true
    
    init(service: EntityService<T>, entityFilter: EntityFilter) {
        self.service = service
        self.entityFilter = entityFilter
    }

    func update() {
        isLoading = true

        service.getAllByFilter(entityFilter: entityFilter, pageFilter: pageFilter)
            .sink(receiveCompletion: { completion in
                self.isLoading = false
                switch completion {
                case .finished:
                    break
                case .failure(let error):
                    NotificationHandler.shared.addNotification(NetworkRequestErrorNotificationContent(networkRequestError: error))
                    print("Error: \(error.getMessage())")
                }
            }, receiveValue: { value in
                self.entityPage = value
            })
            .store(in: &cancellables)
    }

    func updateEntity(_ entity: T) {
        isLoading = true

        service.update(entity: entity)
            .sink(receiveCompletion: { completion in
                self.isLoading = false
                switch completion {
                case .finished:
                    NotificationHandler.shared.addNotification(SuccessfulSaveNotificationContent())
                    self.update()
                    break
                case .failure(let error):
                    NotificationHandler.shared.addNotification(NetworkRequestErrorNotificationContent(networkRequestError: error))
                    print("Error: \(error.getMessage())")
                }
            }, receiveValue: { _ in
            })
            .store(in: &cancellables)
    }

    func deleteEntity(_ entity: T) {
        isLoading = true

        service.delete(entity: entity)
            .sink(receiveCompletion: { completion in
                self.isLoading = false
                switch completion {
                case .finished:
                    NotificationHandler.shared.addNotification(SuccessfulDeleteNotificationContent())
                    self.update()
                    break
                case .failure(let error):
                    NotificationHandler.shared.addNotification(NetworkRequestErrorNotificationContent(networkRequestError: error))
                    print("Error: \(error.getMessage())")
                }
            }, receiveValue: { _ in
            })
            .store(in: &cancellables)
    }
    
    func getCurrentPageNumber() -> UInt {
        if (entityPage.totalPages == 0) {
            return 0
        }
        return entityPage.number + 1
    }
    
    func goToPreviousPage() {
        if (!isFirstPage()) {
            pageFilter.pageNumber -= 1
        }
        update()
    }
    
    func goToNextPage() {
        if (!isLastPage()) {
            pageFilter.pageNumber += 1
        }
        update()
    }
    
    func isFirstPage() -> Bool {
        return entityPage.isFirst
    }
    
    func isLastPage() -> Bool {
        return entityPage.isLast
    }
    
    func getTotalElements() -> UInt {
        return entityPage.totalElements
    }
    
    func getTotalPages() -> UInt {
        return entityPage.totalPages
    }
}
