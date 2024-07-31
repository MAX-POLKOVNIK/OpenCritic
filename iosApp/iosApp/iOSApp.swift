import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        IosApp.shared.doInit(
            stringProvider: IosStringProvider()
        )
    }
	var body: some Scene {
		WindowGroup {
            RouterView {
                MainScreenView()
            }
		}
	}
}
