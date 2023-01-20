package uz.behzoddev.viper_initial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import uz.behzoddev.mvi.data.remote.dto.User
import uz.behzoddev.viper_initial.ui.contract.ViperContract

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ViperContract.ViperView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showData() {
        TODO("Not yet implemented")
    }

    override fun hideData() {
        TODO("Not yet implemented")
    }

    override fun publishData(data: List<User>) {
        TODO("Not yet implemented")
    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }
}