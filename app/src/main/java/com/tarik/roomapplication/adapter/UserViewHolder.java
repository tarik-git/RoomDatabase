package com.tarik.roomapplication.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tarik.roomapplication.databinding.ItemUserBinding;
import com.tarik.roomapplication.model.User;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private ItemUserBinding binding;

    public UserViewHolder(@NonNull ItemUserBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void onBind(User user) {
        binding.firstNameTextView.setText(user.firstName);
        binding.lastNameTextView.setText(user.lastName);
    }

}
