package com.tarik.roomapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.tarik.roomapplication.adapter.UserAdapter;
import com.tarik.roomapplication.databinding.ActivityMainBinding;
import com.tarik.roomapplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CREATE_USER = 200;

    private ActivityMainBinding binding;
    private List<User> userList = new ArrayList<>();
    private UserAdapter userAdapter = new UserAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        binding.recyclerView.setAdapter(userAdapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getBaseContext(), LinearLayout.VERTICAL));

        binding.floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), SecondActivity.class);
            startActivityForResult(intent, REQUEST_CODE_CREATE_USER);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CREATE_USER && data != null) {
                String firstName = data.getStringExtra(SecondActivity.EXTRA_FIRST_NAME);
                String lastName=  data.getStringExtra(SecondActivity.EXTRA_LAST_NAME);
                updateAdapter(firstName, lastName);
            }
        }
    }

    private void updateAdapter(String firstName, String lastName) {
        User user = new User();
        user.firstName = firstName;
        user.lastName = lastName;
        userList.add(user);
        userAdapter.setUserList(userList);
    }

}