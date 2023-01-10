package uz.behzoddev.mvi.ui

import uz.behzoddev.mvi.core.Event

sealed interface MviEvent : Event {
    object Launch: MviEvent
    object ClickButton : MviEvent
}