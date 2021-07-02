package com.example.secondapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;    // Создаём переменную
    ArrayList<User> users = new ArrayList<>();  // Создаём коллекцию список юзеров
    UserAdapter userAdapter;  // Определяем переменную адаптера
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);  // Обращаемся по индентификатору к виджету
       // устанавливаем мэнеджер лайяута
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // Заполняем описание юзеров
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("Пользователь №"+i);
            user.setUserLastName("Фамилия №"+i);
            users.add(user);
        }


        userAdapter = new UserAdapter(users);  // создаём экземпляр адаптера
        recyclerView.setAdapter(userAdapter);  // устанавливаем адаптер
    }

    // ViewHolder описывает представление элемента и метаданные о его месте в RecyclerView.
    private  class UserHolder extends RecyclerView.ViewHolder{
        TextView itemTextView;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            // itemView - текущий layout single_item
            itemTextView = itemView.findViewById(R.id.itemTextView);
        }

        public void bind(String userString){
            itemTextView.setText(userString);
        }
    }

    //Адаптер обеспечивает привязку набора данных для конкретного юзера к представлению,
    // отображаемому в RecyclerView.
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        ArrayList<User> users;
        public UserAdapter(ArrayList<User> users) {
            this.users = users;
        }

        // метод вызывается, когда необходимо создать новый View
        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new UserHolder(inflater, viewGroup);
        }

        // метод вызывается, когда необходимо заполнить View
        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            User user = users.get(position);
            String userString = user.getUserName()+"\n"+user.getUserLastName();
            userHolder.bind(userString);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}