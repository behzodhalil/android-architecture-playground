package uz.behzoddev.viper_initial.ui.contract

import uz.behzoddev.mvi.data.remote.dto.User

interface ViperContract {

    interface ViperView {
        fun showLoading()
        fun hideLoading()
        fun showData()
        fun hideData()
        fun publishData(data: List<User>)
        fun showMessage(message: String)
    }

    interface ViperViewModel {
        fun getData(): List<User>
    }

    interface ViperRouter {
        fun finish()
    }

    interface Presenter {
        fun bindView(view: ViperContract.ViperView)
        fun unbindView()
        fun onViewCreated()
        fun onListClicked()
    }
}