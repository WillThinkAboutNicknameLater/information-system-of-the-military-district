//
//  SidebarView.swift
//  MilitarySystem
//
//  Created by Mackem Meya on 14.05.2022.
//

import SwiftUI

struct SidebarView: View {
    @State private var selectedItem: UUID?
    
    var sidebarDataSection: some View {
        Section("Данные") {
            DisclosureGroup {
                SidebarItemView(title: "Типы формирований", imageName: "info.circle", selectedItem: $selectedItem) {
                    MilitaryFormationTypePageView()
                }
            } label: {
                SidebarItemView(title: "Воинские формирования", imageName: "building.2.fill", selectedItem: $selectedItem) {
                    MilitaryFormationPageView()
                }
            }
            
            DisclosureGroup {
                SidebarItemView(title: "Типы дислокаций", imageName: "info.circle", selectedItem: $selectedItem) {
                    DislocationTypePageView()
                }
            } label: {
                SidebarItemView(title: "Дислокации", imageName: "location.fill", selectedItem: $selectedItem) {
                    DislocationPageView()
                }
            }
            
            SidebarItemView(title: "Субъекты", imageName: "map.fill", selectedItem: $selectedItem) {
                SubjectPageView()
            }
            
            DisclosureGroup {
                SidebarItemView(title: "Категории вооружения", imageName: "info.circle", selectedItem: $selectedItem) {
                    ArmamentCategoryPageView()
                }
            } label: {
                SidebarItemView(title: "Вооружение", imageName: "shield.righthalf.filled", selectedItem: $selectedItem) {
                    ArmamentPageView()
                }
            }
            
            DisclosureGroup {
                SidebarItemView(title: "Группы боевой техники", imageName: "info.circle", selectedItem: $selectedItem) {
                    CombatVehicleGroupPageView()
                }
                SidebarItemView(title: "Категории боевой техники", imageName: "info.circle", selectedItem: $selectedItem) {
                    CombatVehicleCategoryPageView()
                }
            } label: {
                SidebarItemView(title: "Боевая техника", imageName: "car.2.fill", selectedItem: $selectedItem) {
                    CombatVehiclePageView()
                }
            }
            
            DisclosureGroup {
                SidebarItemView(title: "Категории сооружения", imageName: "info.circle", selectedItem: $selectedItem) {
                    MilitaryBuildingCategoryPageView()
                }
            } label: {
                SidebarItemView(title: "Сооружения", imageName: "house.fill", selectedItem: $selectedItem) {
                    MilitaryBuildingPageView()
                }
            }
            
            SidebarItemView(title: "Военные округа", imageName: "bonjour", selectedItem: $selectedItem) {
                MilitaryDistrictPageView()
            }
            
            SidebarItemView(title: "Военнослужащие", imageName: "person.2.fill", selectedItem: $selectedItem) {
                MilitaryManPageView()
            }
            
            DisclosureGroup {
                SidebarItemView(title: "Категории званий", imageName: "info.circle", selectedItem: $selectedItem) {
                    RankCategoryPageView()
                }
                SidebarItemView(title: "Категории составов", imageName: "info.circle", selectedItem: $selectedItem) {
                    StaffCategoryPageView()
                }
            } label: {
                SidebarItemView(title: "Звания", imageName: "person.crop.rectangle.stack.fill", selectedItem: $selectedItem) {
                    RankPageView()
                }
            }
            
            SidebarItemView(title: "Специальности", imageName: "person.crop.square.filled.and.at.rectangle.fill", selectedItem: $selectedItem) {
                MilitarySpecialtyPageView()
            }
        }
    }
    
    var sidebarQuerySection: some View {
        Section("Запросы") {
            EmptyView()
        }
    }
    
    var body: some View {
        List {
            sidebarDataSection
            sidebarQuerySection
        }
        .toolbar {
            ToggleSidebarToolbar()
        }
        .frame(minWidth: 240)
        .padding(8)
    }
}
