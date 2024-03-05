package com.rocqjones.me_logic.models

/**
 * The Type-Safe sealed Screen here represents a closed set of screen
 * states which is useful for handling navigation in our app.
 */
sealed class Screen(val route: String) {
    object HomeScreen : Screen("homeScreen")
    object EndlessScreen : Screen("endlessScreen")
    object BottomSheetDialogScreen : Screen("bottomSheetDialogScreen")
}