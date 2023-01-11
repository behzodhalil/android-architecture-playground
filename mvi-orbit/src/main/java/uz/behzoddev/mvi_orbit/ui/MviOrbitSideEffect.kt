package uz.behzoddev.mvi_orbit.ui

sealed interface MviOrbitSideEffect {
    data class Click(val text: String): MviOrbitSideEffect
    data class Toast(val text: String): MviOrbitSideEffect
    object Hide: MviOrbitSideEffect
}