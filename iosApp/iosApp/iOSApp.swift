import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        IosApp.shared.doInit(
            stringResourceProvider: IosStringResourceProvider(),
            imageResourceProvider: IosImagesResourceProvider(),
            dateFormatter: IosDateFormatter()
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
