package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UserInfoActivity extends AppCompatActivity {

    Button deleteUserBtn;
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextPhone;
    String UUID;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        String text = getIntent().getStringExtra("text");
        UUID = getIntent().getStringExtra("UUID");
        System.out.println(UUID);

        editTextName = findViewById(R.id.editTextName);
        editTextName.setText(text.split("\n")[0]);

        editTextLastName = findViewById(R.id.editTextLastName);
        editTextLastName.setText(text.split("\n")[1]);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPhone.setText(UUID);

        users = new Users(UserInfoActivity.this);

        //users.infoUserByUSERNAME(text.split("\n")[0]);
        users.infoUserByUUID(UUID);
        ArrayList<User> userArrayList = users.getUserList();
        for (User user : userArrayList) {
            if (user.getUuid().toString().contentEquals(UUID)) {
                editTextName.setText(user.getUserName());
                editTextLastName.setText(user.getUserLastName());
                editTextPhone.setText(user.getPhone());
            }
        }

        deleteUserBtn = findViewById(R.id.deleteUserBtn);
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                users.deleteUser(UUID);
                Toast.makeText(UserInfoActivity.this,
                        "user удалён из базы!",
                        Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }
}