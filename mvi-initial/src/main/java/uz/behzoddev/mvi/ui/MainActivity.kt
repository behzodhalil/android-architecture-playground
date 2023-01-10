package uz.behzoddev.mvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzoddev.mvi.MainViewModel
import uz.behzoddev.mvi.data.remote.dto.User
import uz.behzoddev.mvi.databinding.ActivityMainBinding
import uz.behzoddev.mvi.ui.adapter.MainAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        observeEvents()
        observeState()
    }

    private fun setupView() {
        binding.recyclerView.adapter = adapter
    }

    private fun observeEvents() {
        viewModel.renderEvent(MviEvent.Launch)

        binding.btnFetchUser.setOnClickListener {
            viewModel.renderEvent(MviEvent.ClickButton)
        }
    }

    private fun observeState() {
        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { state -> renderState(state) }
            .launchIn(lifecycleScope)
    }

    private fun renderState(state: MviState) {
        when (state.actions) {
            MviAction.Available -> {
                handleVisibilityItems(MviAction.Available)
                drawUsers(state.users)
            }
            MviAction.Failure -> {
                handleVisibilityItems(MviAction.Failure)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            MviAction.Idle -> {
                handleVisibilityItems(MviAction.Idle)
            }
            MviAction.Loading -> handleVisibilityItems(MviAction.Loading)
        }
    }

    private fun handleVisibilityItems(action: MviAction) {
        when (action) {
            MviAction.Available -> {
                binding.btnFetchUser.visibility = View.GONE
                binding.progressBar.visibility = View.INVISIBLE
            }
            MviAction.Failure -> {
                binding.btnFetchUser.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
            }
            MviAction.Idle -> {
                binding.btnFetchUser.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
            }
            MviAction.Loading -> {
                binding.btnFetchUser.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun drawUsers(users: List<User>) {
        binding.recyclerView.visibility = View.VISIBLE
        adapter.submitList(users)
    }
}