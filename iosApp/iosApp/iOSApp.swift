import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        IosApp.shared.doInit()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
