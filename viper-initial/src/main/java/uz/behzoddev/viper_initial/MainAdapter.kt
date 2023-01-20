package uz.behzoddev.viper_initial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.behzoddev.mvi.data.remote.dto.User
import uz.behzoddev.viper_initial.databinding.ItemLayoutBinding

class MainAdapter : ListAdapter<User, MainAdapter.DataViewHolder>(diffUtil) {

    inner class DataViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                tvUserEmail.text = user.email
                tvUserName.text = user.name
                Glide.with(ivAvatar.context)
                    .load(user.avatar)
                    .into(ivAvatar)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(currentList[position])


}