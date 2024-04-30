import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        IosApp.shared.doInit(
            stringResourceProvider: IosStringResourceProvider(),
            imageResourceProvider: IosImagesResourceProvider()
        )
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
