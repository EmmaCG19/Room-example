package com.example.tutoroomdevkiper.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tutoroomdevkiper.databinding.ItemUserBinding
import com.example.tutoroomdevkiper.domain.User

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemUserBinding.bind(itemView)

    fun render(userModel: User) {
        binding.tvName.text = userModel.name
        binding.tvSurname.text = userModel.surname
    }
}