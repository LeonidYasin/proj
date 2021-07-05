package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserInfoActivity extends AppCompatActivity {

    Button insertUserBtn;
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        String text = getIntent().getStringExtra("text");

        editTextName = findViewById(R.id.editTextName);
        editTextName.setText(text.split("\n")[0]);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextLastName.setText(text.split("\n")[1]);
        editTextPhone = findViewById(R.id.editTextPhone);
        insertUserBtn = findViewById(R.id.insertUserBtn);
        insertUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // User user = new User();
              //  user.setUserName(editTextName.getText().toString());
              //  user.setUserLastName(editTextLastName.getText().toString());
               // user.setPhone(editTextPhone.getText().toString());
               // Users users = new Users(UserFormActivity.this);
              //  users.addUser(user);
               // onBackPressed();
            }
        });

    }
}