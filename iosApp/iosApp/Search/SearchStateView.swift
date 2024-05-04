//
//  SearchStateView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct SearchStateView: View {
    let state: SearchState
    
    @State private var criteria: String = ""
    
    var body: some View {
        ScrollView {
            LazyVStack {
                SearchBar(text: $criteria, placeholder: state.searchHint)
                    .onReceive(Just(criteria)) { _ in
                        state.changeSearch(criteria: criteria)
                    }
                    .padding()
                
                switch state.searchListItemsState {
                case let content as SearchItemsStateContent:
                    ForEach(content.items, id: \.self) { item in
                        SearchListItemView(item: item)
                            .padding(.horizontal)
                    }
                case _ as SearchItemsStateLoading:
                    DashboardStateLoadingView(state: DashboardStateLoading.shared)
                case let empty as SearchItemsStateEmpty:
                    DashboardStateErrorView(state: DashboardStateError(error: empty.message))
                case let error as SearchItemsStateError:
                    DashboardStateErrorView(state: DashboardStateError(error: error.message))
                default:
                    Spacer()
                }
            }
        }
        .onAppear(perform: UIApplication.shared.addTapGestureRecognizer)
    }
}

struct SearchBar: UIViewRepresentable {

    @Binding var text: String
    let placeholder: String

    class Coordinator: NSObject, UISearchBarDelegate {

        @Binding var text: String

        init(text: Binding<String>) {
            _text = text
        }

        func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
            text = searchText
        }
    }
    func makeCoordinator() -> SearchBar.Coordinator {
        return Coordinator(text: $text)
    }

    func makeUIView(context: UIViewRepresentableContext<SearchBar>) -> UISearchBar {
        let searchBar = UISearchBar(frame: .zero)
        searchBar.placeholder = placeholder
        searchBar.delegate = context.coordinator
        searchBar.autocapitalizationType = .none
        searchBar.backgroundImage = UIImage()
        return searchBar
    }

    func updateUIView(_ uiView: UISearchBar, context: UIViewRepresentableContext<SearchBar>) {
        uiView.text = text
    }
}

#Preview {
    SearchStateView(
        state: SearchState(
            searchText: "Game to find",
            searchHint: "Type something",
            searchListItemsState: SearchItemsStateContent(
                items: [
                    SearchListItem(
                        id: 1,
                        nameText: "Game name",
                        kindText: "Game",
                        kindColor: Colors.shared.Orange,
                        onClick: { _ in }
                    ),
                    SearchListItem(
                        id: 1,
                        nameText: "Some magazine",
                        kindText: "Outlet",
                        kindColor: Colors.shared.Cyan,
                        onClick: { _ in }
                    ),
                    SearchListItem(
                        id: 1,
                        nameText: "Max Polkovnik",
                        kindText: "Critic",
                        kindColor: Colors.shared.Purple,
                        onClick: { _ in }
                    ),
                ]
            ),
            onSearchChanged: { _, _ in }
        )
    )
}

extension UIApplication {
    func addTapGestureRecognizer() {
        guard let window = windows.first else { return }
        let tapGesture = UITapGestureRecognizer(target: window, action: #selector(UIView.endEditing))
        tapGesture.requiresExclusiveTouchType = false
        tapGesture.cancelsTouchesInView = false
        tapGesture.delegate = self
        window.addGestureRecognizer(tapGesture)
    }
}

extension UIApplication: UIGestureRecognizerDelegate {
    public func gestureRecognizer(_ gestureRecognizer: UIGestureRecognizer, shouldRecognizeSimultaneouslyWith otherGestureRecognizer: UIGestureRecognizer) -> Bool {
        return true // set to `false` if you don't want to detect tap during other gestures
    }
}
