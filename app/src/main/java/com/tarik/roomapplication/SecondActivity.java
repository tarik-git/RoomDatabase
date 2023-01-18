package com.tarik.roomapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.tarik.roomapplication.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_FIRST_NAME = "EXTRA_FIRST_NAME";
    public static final String EXTRA_LAST_NAME = "EXTRA_LAST_NAME";

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowHomeEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_item_save) {

            String firstName = binding.firstNameEditText.getText().toString();
            String secondName = binding.lastNameEditText.getText().toString();
            Intent saveDataIntent = new Intent();
            saveDataIntent.putExtra(EXTRA_FIRST_NAME, firstName);
            saveDataIntent.putExtra(EXTRA_LAST_NAME, secondName);
            setResult(RESULT_OK, saveDataIntent);
            finish();

            return true;
        }
        return false;
    }
}