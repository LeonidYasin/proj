package com.example.secondapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserFormActivity extends AppCompatActivity {
    Button insertUserBtn;
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextPhone;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);
        user = (User) getIntent().getSerializableExtra("user");
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        insertUserBtn = findViewById(R.id.insertUserBtn);
        boolean isUpdate = user != null;
        if (isUpdate) { // Если редактируем юзера
            editTextName.setText(user.getUserName());
            editTextLastName.setText(user.getUserLastName());
            editTextPhone.setText(user.getPhone());
            insertUserBtn.setText("Изменить запись");
        } else { // если добавляем нового
            user = new User();
            insertUserBtn.setText("Добавить запись");
        }

        insertUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setUserName(editTextName.getText().toString());
                user.setUserLastName(editTextLastName.getText().toString());
                user.setPhone(editTextPhone.getText().toString());
                Users users = new Users(UserFormActivity.this);
                if (isUpdate) users.updateUser(user);
                else users.addUser(user);
                onBackPressed();
            }
        });

    }
}

