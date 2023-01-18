package com.tarik.roomapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.tarik.roomapplication.adapter.UserAdapter;
import com.tarik.roomapplication.database.AppDatabase;
import com.tarik.roomapplication.databinding.ActivityMainBinding;
import com.tarik.roomapplication.model.User;
import com.tarik.roomapplication.model.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.UserClickListener{

    private static final int REQUEST_CODE_CREATE_USER = 200;

    private ActivityMainBinding binding;
    private List<User> userList = new ArrayList<>();
    private UserAdapter userAdapter = new UserAdapter(this);

    private AppDatabase appDatabase = null;
    private UserDao userDao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appDatabase = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "room_app_database")
                .allowMainThreadQueries()
                .build();
        userDao = appDatabase.userDao();

        userList = userDao.getAllUsers();
        userAdapter.setUserList(userList);

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
                String lastName =  data.getStringExtra(SecondActivity.EXTRA_LAST_NAME);
                updateAdapter(firstName, lastName);
            }
        }
    }

    private void updateAdapter(String firstName, String lastName) {
        User user = new User();
        user.firstName = firstName;
        user.lastName = lastName;
        long id = userDao.insertUser(user);
        user.uid = (int)id;


        userList.add(user);
        userAdapter.setUserList(userList);
    }

    @Override
    public void onClick(User user) {

    }

    @Override
    public boolean onLongClick(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to delete user " + user.firstName + " " + user.lastName)
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    if (userDao != null) {
                        userDao.deleteUser(user);
                        userList.remove(user);
                        userAdapter.setUserList(userList);
                    }
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss());
        builder.show();
        return true;
    }
}