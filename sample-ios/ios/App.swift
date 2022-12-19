import SwiftUI
import ui

@main
struct UiApp : App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

struct ContentView: View {
    var body: some View {
        VStack {
            Text("Nothing here since inline class gets inlined here.")
        }
    }
}
