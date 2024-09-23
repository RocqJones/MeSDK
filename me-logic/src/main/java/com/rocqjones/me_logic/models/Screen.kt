package com.rocqjones.me_logic.models

/**
 * The Type-Safe sealed Screen here represents a closed set of screen
 * states which is useful for handling navigation in our app.
 */
sealed class Screen(val route: String) {
    data object HomeScreen : Screen("homeScreen")
    data object EndlessScreen : Screen("endlessScreen")
    data object BottomSheetDialogScreen : Screen("bottomSheetDialogScreen")
    data object GenHomeScreen : Screen("genHomeScreen")
    data object VoiceInputScreen : Screen("voiceInputScreen")
}