package uz.behzoddev.mvi_orbit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.viewmodel.observe
import uz.behzoddev.mvi_orbit.databinding.ActivityMainBinding
import uz.behzoddev.mvi_orbit.ui.MviOrbitSideEffect
import uz.behzoddev.mvi_orbit.ui.MviOrbitState
import uz.behzoddev.mvi_orbit.ui.MviOrbitViewModel
import uz.behzoddev.mvi_orbit.ui.adapter.MainAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MviOrbitViewModel by viewModels()

    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        observeState()
        observeEffects()
    }

    private fun observeState() {
        viewModel.container.stateFlow
            .flowWithLifecycle(lifecycle)
            .onEach { renderState(it) }
            .launchIn(lifecycleScope)
    }

    private fun observeEffects() {
        viewModel.container.sideEffectFlow
            .flowWithLifecycle(lifecycle)
            .onEach { sideEffects(it) }
            .launchIn(lifecycleScope)
    }

    private fun setupView() {
        binding.recyclerView.adapter = adapter
        binding.btnFetchUser.setOnClickListener {
            viewModel.clickFetchUser()
        }
    }

    private fun renderState(state: MviOrbitState) {
        if (state.users.isNotEmpty()) {
            adapter.submitList(state.users)
        }
    }

    private fun sideEffects(sideEffect: MviOrbitSideEffect) {
        when (sideEffect) {
            is MviOrbitSideEffect.Toast -> {
                Toast.makeText(this, sideEffect.text, Toast.LENGTH_SHORT).show()
            }
            is MviOrbitSideEffect.Click -> {
                Toast.makeText(this, sideEffect.text, Toast.LENGTH_SHORT).show()
            }
            MviOrbitSideEffect.Hide -> {
                binding.recyclerView.visibility = View.VISIBLE
                binding.btnFetchUser.visibility = View.INVISIBLE
            }
        }
    }

}