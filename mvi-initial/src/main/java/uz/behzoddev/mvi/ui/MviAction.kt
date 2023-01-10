package uz.behzoddev.mvi.ui

import uz.behzoddev.mvi.core.Action

sealed interface MviAction: Action {
    object Idle: MviAction
    object Failure: MviAction
    object Available: MviAction
    object Loading: MviAction
}